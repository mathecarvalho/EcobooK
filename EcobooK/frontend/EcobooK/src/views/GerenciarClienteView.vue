<template>
    <div class="wrapper py-5">
        <div class="container-fluid">
            <div class="container-fluid mb-4">
                <!-- Topo: Marca, Barra de busca e Botão -->
                <div class="d-flex align-items-center justify-content-between flex-wrap">

                    <!-- Marca no canto esquerdo -->
                    <div class="me-3"><h2 class="fw-bold text-dark m-0">EcobooK</h2></div>

                    <!-- Barra de busca centralizada -->
                    <div class="mx-2 flex-grow-1" style="min-width: 200px; max-width: 300px;">
                        <div class="input-group input-group-sm">
                            <input type="search" class="form-control border-dark" v-model="search" placeholder="Buscar clientes..."/>
                            <span class="input-group-text bg-white border-dark"><i class="bi bi-search fs-5"></i></span>
                        </div>
                    </div>

                    <!-- Botão Novo Cliente -->
                    <router-link class="btn btn-dark fw-bold" to="/adm/clientes/cadastrar">➕ Novo Cliente</router-link>
                </div>
            </div>

            <!-- Lista de Clientes -->
            <div class="row g-4">
                <div v-for="cliente in clientesFiltrados" :key="cliente.id" class="col-md-6 col-lg-4">
                    <div class="card shadow-sm h-100 d-flex flex-row p-3 align-items-center">

                        <!-- Checkbox -->
                        <div class="form-check me-3">
                            <input class="form-check-input" type="checkbox" v-model="selecionados" :value="cliente.id"/>
                        </div>

                        <!-- Informações -->
                        <div class="flex-grow-1">
                            <h5 class="fw-bold mb-1">{{ cliente.nome }}</h5>
                            <p class="mb-1 text-muted">{{ cliente.email }}</p>
                            <p class="mb-1"><strong>Telefone:</strong> {{ cliente.telefone }}</p>
                            <p class="mb-1"><strong>Data de Cadastro:</strong> {{ cliente.dataCadastro }}</p>

                            <!-- Status -->
                            <p class="mb-2">
                                <strong>Status: </strong>
                                <span :class="cliente.status ? 'text-success' : 'text-danger'">
                                    {{ cliente.status ? 'Ativo' : 'Inativo' }}
                                </span>
                            </p>

                            <!-- Ações -->
                            <div>
                                <button class="btn btn-sm btn-dark bg-gradient me-2" @click="editarCliente(cliente.id)">✏️ Editar</button>
                                <button class="btn btn-sm btn-danger bg-gradient me-2" @click="removerCliente(cliente.id)">🗑️ Remover</button>
                                <button
                                    class="btn btn-sm"
                                    :class="cliente.status ? 'btn-secondary' : 'btn-success'"
                                    @click="toggleAtivo(cliente)"
                                >
                                    {{ cliente.status ? 'Inativar' : 'Ativar' }}
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Ações em lote -->
            <div class="mt-4 d-flex justify-content-end">
                <button class="btn btn-danger bg-gradient me-2" :disabled="selecionados.length === 0" @click="removerSelecionados">
                    🗑️ Remover Selecionados ({{ selecionados.length }})
                </button>
                <button class="btn btn-dark bg-gradient me-2" :disabled="selecionados.length === 0" @click="editarSelecionados">
                    ✏️ Editar Selecionados ({{ selecionados.length }})
                </button>
                <button class="btn btn-success bg-gradient" :disabled="selecionados.length === 0" @click="ativarSelecionados">
                    ✅ Ativar Selecionados
                </button>
                <button class="btn btn-secondary bg-gradient ms-2" :disabled="selecionados.length === 0" @click="inativarSelecionados">
                    🚫 Inativar Selecionados
                </button>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from "vue";
import { useRouter } from "vue-router";
import api from "@/service/api";

const router = useRouter();

interface Cliente {
    id: number;
    nome: string;
    email: string;
    telefone: string;
    dataCadastro: string;
    status: boolean;
}

const clientes = ref<Cliente[]>([]);
const selecionados = ref<number[]>([]);
const search = ref("");

// 🔹 Buscar clientes ao carregar
onMounted(async () => {
    try {
        const { data } = await api.get(`/cliente/listar`);
        console.log("Clientes carregados:", data);

        clientes.value = data.map((c: any) => ({
            id: c.id,
            nome: `${c.nome} ${c.sobrenome}`, // junta nome + sobrenome
            email: c.email,
            telefone: c.telefone ?? "Não informado", // opcional
            dataCadastro: c.dataCadastro ?? "—",     // opcional
            status: c.status                          // backend usa "status"
        }));
    } catch (error) {
        console.error("Erro ao carregar clientes:", error);
    }
});


const clientesFiltrados = computed(() =>
    clientes.value.filter(c =>
        c.nome.toLowerCase().includes(search.value.toLowerCase()) ||
        c.email.toLowerCase().includes(search.value.toLowerCase())
    )
);

function editarCliente(id: number) {
    router.push({ name: "ClienteEditar", params: { id } });
}

async function removerCliente(id: number) {
    if (confirm("Deseja realmente remover este cliente?")) {
        try {
            await api.delete(`/cliente/${id}`);
            clientes.value = clientes.value.filter(c => c.id !== id);
            selecionados.value = selecionados.value.filter(s => s !== id);
        } catch (error) {
            console.error("Erro ao remover cliente:", error);
        }
    }
}

async function removerSelecionados() {
    if (confirm("Deseja remover os clientes selecionados?")) {
        try {
            await Promise.all(
                selecionados.value.map(id => api.delete(`/cliente/${id}`))
            );
            clientes.value = clientes.value.filter(c => !selecionados.value.includes(c.id));
            selecionados.value = [];
        } catch (error) {
            console.error("Erro ao remover selecionados:", error);
        }
    }
}

function editarSelecionados() {
    alert("Editar clientes selecionados: " + selecionados.value.join(", "));
}

// 🔹 Ativar / Inativar cliente individual
async function toggleAtivo(cliente: Cliente) {
    try {
        await api.patch(`/cliente/status`, { id: cliente.id, status: !cliente.status });
        console.log(cliente.id);
        cliente.status = !cliente.status; // atualiza local
    } catch (error) {
        console.error("Erro ao atualizar status:", error);
    }
}

// 🔹 Ativar/Inativar em lote
async function ativarSelecionados() {
    try {
        await Promise.all(
            selecionados.value.map(id =>
                api.patch(`/cliente/status`, { id, status: true })
            )
        );
        clientes.value.forEach(c => {
            if (selecionados.value.includes(c.id)) c.status = true;
        });
    } catch (error) {
        console.error("Erro ao ativar selecionados:", error);
    }
}

async function inativarSelecionados() {
    try {
        await Promise.all(
            selecionados.value.map(id =>
                api.patch(`/cliente/status`, { id, status: false })
            )
        );
        clientes.value.forEach(c => {
            if (selecionados.value.includes(c.id)) c.status = false;
        });
    } catch (error) {
        console.error("Erro ao inativar selecionados:", error);
    }
}
</script>

<style scoped>
.wrapper {
    background-color: #f8f8f8;
    min-height: 100vh;
}

.card {
    transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
}

.card:hover {
    transform: translateY(-3px);
    box-shadow: 0 6px 20px rgba(0,0,0,0.15);
}

.btn-dark, .btn-danger, .btn-warning, .btn-success {
    transition: all 0.2s ease-in-out;
}

.btn-dark:hover {
    background-color: #222;
}

.btn-danger:hover {
    background-color: #c00;
}
</style>

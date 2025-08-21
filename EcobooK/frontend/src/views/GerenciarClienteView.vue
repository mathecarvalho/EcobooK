<template>
    <div class="page-wrapper">
        <div class="container my-5">
            <div class="card shadow p-4 rounded-3">
                <h2 class="mb-4 text-center">Gerenciamento de Clientes</h2>

                <!-- Barra de busca -->
                <div class="d-flex mb-3">
                    <input
                        type="text"
                        v-model="search"
                        class="form-control me-2"
                        placeholder="Buscar por nome, e-mail ou CPF"
                    />
                    <button class="btn btn-dark">Buscar</button>
                </div>

                <!-- Botão de adicionar -->
                <div class="d-flex justify-content-end mb-3">
                    <button class="btn btn-success" @click="adicionarCliente">
                        + Adicionar Cliente
                    </button>
                </div>

                <!-- Tabela de usuários -->
                <div class="table-responsive">
                    <table class="table table-striped table-hover align-middle">
                        <thead class="table-dark">
                        <tr>
                            <th>Nome</th>
                            <th>Email</th>
                            <th>Telefone</th>
                            <th>CPF</th>
                            <th>Status</th>
                            <th>Data de Cadastro</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="cliente in filteredClientes" :key="cliente.id" class="cursor-pointer" @click="editarCliente(cliente)">
                            <td>{{ cliente.nome }}</td>
                            <td>{{ cliente.email }}</td>
                            <td>{{ cliente.telefone }}</td>
                            <td>{{ cliente.cpf }}</td>
                            <td>
                                <span class="badge" :class="cliente.ativo ? 'bg-success' : 'bg-secondary'">{{ cliente.ativo ? 'Ativo' : 'Inativo' }}</span>
                            </td>
                            <td>{{ cliente.dataCadastro }}</td>
                            <td>
                                <button class="btn btn-sm btn-danger" @click.stop="excluirCliente(cliente.id)">Excluir</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, computed } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const clientes = ref([
    { id: 1, nome: "Ana Souza", email: "ana@email.com", telefone: "11999999999", cpf: "123.456.789-00", ativo: true, dataCadastro: "10/08/2025" },
    { id: 2, nome: "João Silva", email: "joao@email.com", telefone: "11988888888", cpf: "987.654.321-00", ativo: false, dataCadastro: "05/08/2025" },
    { id: 3, nome: "Maria Oliveira", email: "maria@email.com", telefone: "21977777777", cpf: "456.789.123-00", ativo: true, dataCadastro: "01/08/2025" },
]);

const search = ref("");

// Filtro de busca
const filteredClientes = computed(() => {
    if (!search.value) return clientes.value;
    return clientes.value.filter(
        c =>
            c.nome.toLowerCase().includes(search.value.toLowerCase()) ||
            c.email.toLowerCase().includes(search.value.toLowerCase()) ||
            c.cpf.includes(search.value)
    );
});

// Função para navegar para a tela de cadastro
function adicionarCliente() {
    router.push("/adm/clientes/cadastrar");
}

// Navegar para a tela de edição
function editarCliente(cliente: any) {
    router.push(`/adm/clientes/editar/${cliente.id}`);
}

function excluirCliente(id: number) {
    clientes.value = clientes.value.filter(c => c.id !== id);
}
</script>

<style scoped>
.page-wrapper {
    background-color: #f8f8f8;
    min-height: 100vh;
    padding: 20px;
}
.cursor-pointer {
    cursor: pointer;
}
</style>

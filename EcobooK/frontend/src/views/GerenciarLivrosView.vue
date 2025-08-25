<template>
    <div class="wrapper py-5">
        <div class="container-fluid">
            <div class="container-fluid mb-4">
                <!-- Topo: Marca, Barra de busca e Botão -->
                <div class="d-flex align-items-center justify-content-between flex-wrap">
                    <div class="me-3"><h2 class="fw-bold text-dark m-0">EcobooK</h2></div>
                    <div class="mx-2 flex-grow-1" style="min-width: 200px; max-width: 300px;">
                        <div class="input-group input-group-sm">
                            <input type="search" class="form-control border-dark" v-model="search" placeholder="Buscar livros..."/>
                            <span class="input-group-text bg-white border-dark"><i class="bi bi-search fs-5"></i></span>
                        </div>
                    </div>
                    <router-link class="btn btn-dark fw-bold" to="/adm/livros/cadastrar">➕ Novo Livro</router-link>
                </div>
            </div>

            <!-- Lista de Livros -->
            <div class="row g-4">
                <div v-for="produto in produtosFiltrados" :key="produto.id" class="col-md-6 col-lg-4">
                    <div class="card shadow-sm h-100 d-flex flex-row p-3 align-items-center">
                        <!-- Checkbox -->
                        <div class="form-check me-3">
                            <input class="form-check-input" type="checkbox" v-model="selecionados" :value="produto.id"/>
                        </div>

                        <!-- Imagem -->
                        <img :src="produto.imagem" alt="Capa do livro" class="img-thumbnail me-3" style="width: 100px; height: 140px; object-fit: cover;"/>

                        <!-- Informações -->
                        <div class="flex-grow-1">
                            <h5 class="fw-bold mb-1">{{ produto.titulo }}</h5>
                            <p class="mb-1 text-muted">{{ produto.autor }}</p>
                            <p class="mb-1"><strong>Editora:</strong> {{ produto.editora }}</p>
                            <p class="mb-1 d-flex justify-content-between">
                                <span><strong>Ano:</strong> {{ produto.anoAtualizacao }}</span>
                                <span><strong>Estoque:</strong> {{ produto.estoque }}</span>
                            </p>
                            <p class="mb-1 d-flex justify-content-between align-items-center">
                                <span class="fw-semibold">R$ {{ produto.preco.toFixed(2) }}</span>
                                <span :class="produto.ativo ? 'text-success fw-bold' : 'text-danger fw-bold'">{{ produto.ativo ? 'Ativo' : 'Inativo' }}</span>
                            </p>

                            <!-- Ações -->
                            <div class="d-flex flex-wrap gap-2">
                                <button class="btn btn-sm btn-dark bg-gradient" @click="editarLivro(produto.id)">✏️ Editar</button>
                                <button class="btn btn-sm btn-danger bg-gradient" @click="removerLivro(produto.id)">🗑️ Remover</button>
                                <button
                                    class="btn btn-sm"
                                    :class="produto.ativo ? 'btn-secondary' : 'btn-success'"
                                    @click="toggleAtivo(produto)"
                                >
                                    {{ produto.ativo ? 'Inativar' : 'Ativar' }}
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Ações em lote -->
            <div class="mt-4 d-flex justify-content-end">
                <button class="btn btn-danger bg-gradient me-2" :disabled="selecionados.length === 0" @click="removerSelecionados">🗑️ Remover Selecionados ({{ selecionados.length }})</button>
                <button class="btn btn-dark bg-gradient" :disabled="selecionados.length === 0" @click="editarSelecionados">✏️ Editar Selecionados ({{ selecionados.length }})</button>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, computed } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

interface Produto {
    id: number;
    titulo: string;
    autor: string;
    preco: number;
    imagem: string;
    editora: string;
    anoAtualizacao: number;
    estoque: number;
    ativo: boolean;
}

const produtos = ref<Produto[]>([
    { id: 1, titulo: "Clean Code", autor: "Robert C. Martin", preco: 120.0, imagem: "https://m.media-amazon.com/images/I/41xShlnTZTL._SX374_BO1,204,203,200_.jpg", editora: "Prentice Hall", anoAtualizacao: 2020, estoque: 10, ativo: true },
    { id: 2, titulo: "Use a Cabeça! Java", autor: "Kathy Sierra, Bert Bates", preco: 129.9, imagem: "https://m.media-amazon.com/images/I/610D1O8WWOL._SY425_BO1,204,203,200_.jpg", editora: "Alta Books", anoAtualizacao: 2015, estoque: 5, ativo: true },
    { id: 3, titulo: "Domain-Driven Design", autor: "Eric Evans", preco: 149.9, imagem: "https://m.media-amazon.com/images/I/61aIS4n2jZL._SY466_BO1,204,203,200_.jpg", editora: "Addison-Wesley", anoAtualizacao: 2016, estoque: 2, ativo: false },
    // demais livros...
]);

const selecionados = ref<number[]>([]);
const search = ref("");

const produtosFiltrados = computed(() =>
    produtos.value.filter(p =>
        p.titulo.toLowerCase().includes(search.value.toLowerCase()) ||
        p.autor.toLowerCase().includes(search.value.toLowerCase())
    )
);

function editarLivro(id: number) {
    router.push({ name: "LivroEditar", params: { id: id } });
}

function removerLivro(id: number) {
    if (confirm("Deseja realmente remover este livro?")) {
        produtos.value = produtos.value.filter(p => p.id !== id);
        selecionados.value = selecionados.value.filter(s => s !== id);
    }
}

function removerSelecionados() {
    if (confirm("Deseja remover os livros selecionados?")) {
        produtos.value = produtos.value.filter(p => !selecionados.value.includes(p.id));
        selecionados.value = [];
    }
}

function editarSelecionados() {
    alert("Editar livros selecionados: " + selecionados.value.join(", "));
}

// Alterna o status ativo/inativo
function toggleAtivo(produto: Produto) {
    produto.ativo = !produto.ativo;
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

.img-thumbnail {
    border-radius: 4px;
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

.btn-warning {
    color: #fff;
    background-color: #ff9800;
}

.btn-warning:hover {
    background-color: #e68900;
}

.btn-success {
    color: #fff;
    background-color: #28a745;
}

.btn-success:hover {
    background-color: #218838;
}
</style>

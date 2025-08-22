<template>
    <div class="wrapper py-5">
        <div class="container-fluid">
            <div class="container-fluid mb-4">
                <!-- Título da Tela -->
<!--                <h3 class="text-center fw-bold mb-3">Livros Cadastrados</h3>-->

                <!-- Topo: Marca, Barra de busca e Botão -->
                <div class="d-flex align-items-center justify-content-between flex-wrap">

                    <!-- Marca no canto esquerdo -->
                    <div class="me-3"><h2 class="fw-bold text-dark m-0">EcobooK</h2></div>

                    <!-- Barra de busca centralizada -->
                    <div class="mx-2 flex-grow-1" style="min-width: 200px; max-width: 300px;">
                        <div class="input-group input-group-sm">
                            <input type="search" class="form-control border-dark" v-model="search" placeholder="Buscar livros..."/>
                            <span class="input-group-text bg-white border-dark"><i class="bi bi-search fs-5"></i></span>
                        </div>
                    </div>

                    <!-- Botão Novo Livro -->
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
                            <p class="mb-1"><strong>Ano:</strong> {{ produto.anoAtualizacao }}</p>
                            <p class="mb-2 fw-semibold">R$ {{ produto.preco.toFixed(2) }}</p>

                            <!-- Ações -->
                            <div>
                                <button class="btn btn-sm btn-dark bg-gradient me-2" @click="editarLivro(produto.id)">✏️ Editar</button>
                                <button class="btn btn-sm btn-danger bg-gradient" @click="removerLivro(produto.id)">🗑️ Remover</button>
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
}

const produtos = ref<Produto[]>([
    { id: 1, titulo: "Clean Code", autor: "Robert C. Martin", preco: 120.0, imagem: "https://m.media-amazon.com/images/I/41xShlnTZTL._SX374_BO1,204,203,200_.jpg", editora: "Prentice Hall", anoAtualizacao: 2020 },
    { id: 2, titulo: "Use a Cabeça! Java", autor: "Kathy Sierra, Bert Bates", preco: 129.9, imagem: "https://m.media-amazon.com/images/I/610D1O8WWOL._SY425_BO1,204,203,200_.jpg", editora: "Alta Books", anoAtualizacao: 2015 },
    { id: 3, titulo: "Domain-Driven Design", autor: "Eric Evans", preco: 149.9, imagem: "https://m.media-amazon.com/images/I/61aIS4n2jZL._SY466_BO1,204,203,200_.jpg", editora: "Addison-Wesley", anoAtualizacao: 2016 },
    { id: 4, titulo: "Clean Architecture", autor: "Robert C. Martin", preco: 139.9, imagem: "https://m.media-amazon.com/images/I/41-sN-mzwKL._SX374_BO1,204,203,200_.jpg", editora: "Prentice Hall", anoAtualizacao: 2017 },
    { id: 5, titulo: "The Pragmatic Programmer", autor: "Andrew Hunt, David Thomas", preco: 99.9, imagem: "https://m.media-amazon.com/images/I/41as+WafrFL._SX380_BO1,204,203,200_.jpg", editora: "Addison-Wesley", anoAtualizacao: 2019 },
    { id: 6, titulo: "Introdução à Linguagem SQL", autor: "Thomas Nield", preco: 139.9, imagem: "https://m.media-amazon.com/images/I/711siL1zU1L._SY425_BO1,204,203,200_.jpg", editora: "Novatec", anoAtualizacao: 2016 },
    { id: 7, titulo: "Use a Cabeça!: Programação JavaScript", autor: "Eric FreemanEric Freeman", preco: 109.9, imagem: "https://m.media-amazon.com/images/I/71ytnrVAjaL._SY466_BO1,204,203,200_.jpg", editora: "Alta Books", anoAtualizacao: 2016 },
    { id: 8, titulo: "Orientação a Objetos e SOLID Para Ninjas", autor: "Mauricio Aniche", preco: 99.9, imagem: "https://m.media-amazon.com/images/I/61N84VgZ4wL._SY466_.jpg", editora: "Casa do Código", anoAtualizacao: 2015 }
]);

const selecionados = ref<number[]>([]);
const search = ref("");

const produtosFiltrados = computed(() =>
    produtos.value.filter(p =>
        p.titulo.toLowerCase().includes(search.value.toLowerCase()) ||
        p.autor.toLowerCase().includes(search.value.toLowerCase())
    )
);

function adicionarLivro() {
    alert("Ir para cadastro de novo livro");
}

function editarLivro(id: number) {
    router.push({ name: "LivroEditar", params: { id: id } });
    // alert("Editar livro ID: " + id);
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

.btn-dark, .btn-danger {
    transition: all 0.2s ease-in-out;
}

.btn-dark:hover {
    background-color: #222;
}

.btn-danger:hover {
    background-color: #c00;
}
</style>

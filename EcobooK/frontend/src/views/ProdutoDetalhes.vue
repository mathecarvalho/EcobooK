<template>
    <div class="container my-5">
        <button class="btn btn-outline-secondary mb-4" @click="$router.back()">
            <i class="bi bi-arrow-left me-2"></i> Voltar
        </button>

        <div class="card shadow-lg p-4 rounded-4">
            <div class="row g-4">
                <!-- Imagem do livro -->
                <div class="col-md-4 text-center">
                    <img :src="produto.imagem" class="img-fluid rounded shadow-sm" />
                </div>

                <!-- Detalhes do livro -->
                <div class="col-md-8 d-flex flex-column justify-content-between">
                    <div>
                        <h2 class="fw-bold">{{ produto.titulo }}</h2>
                        <p class="text-muted mb-1">
                            <i class="bi bi-person-fill me-2"></i>{{ produto.autor }}
                        </p>
                        <p class="text-muted mb-3">
                            <i class="bi bi-building me-2"></i>{{ produto.editora }} • {{ produto.anoAtualizacao }}
                        </p>

                        <h3 class="text-success fw-bold">
                            R$ {{ produto.preco.toFixed(2) }}
                            <span class="badge bg-success ms-2">Em estoque</span>
                        </h3>

                        <h5 class="mt-4 mb-2">Descrição</h5>
                        <p class="text-secondary" style="max-height: 200px; overflow-y: auto;">
                            {{ produto.descricao || "Descrição não disponível." }}
                        </p>
                    </div>

                    <div class="mt-3">
                        <button class="btn btn-dark btn-lg w-100" @click="adicionarAoCarrinho(produto)">
                            <i class="bi bi-cart-plus me-2"></i> Adicionar ao Carrinho
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { useRoute } from "vue-router";

interface Produto {
    id: number
    titulo: string
    autor: string
    preco: number
    imagem: string
    editora: string
    anoAtualizacao: number
    descricao?: string
}

const route = useRoute();
const id = Number(route.params.id);

const produtos: Produto[] = [
    {
        id: 1,
        titulo: "Clean Code",
        autor: "Robert C. Martin",
        preco: 120.0,
        imagem: "https://m.media-amazon.com/images/I/41xShlnTZTL._SX374_BO1,204,203,200_.jpg",
        editora: "Prentice Hall",
        anoAtualizacao: 2020,
        descricao: "Um guia essencial sobre boas práticas de programação."
    },
    // ... demais produtos
];

const produto = produtos.find(p => p.id === id)!;

function adicionarAoCarrinho(produto: Produto) {
    console.log("Adicionado ao carrinho:", produto);
}
</script>

<style scoped>
/* Pequenos ajustes de hover e sombra */
.card img:hover {
    transform: scale(1.03);
    transition: transform 0.3s;
}
</style>

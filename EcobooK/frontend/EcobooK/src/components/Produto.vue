<template>
    <div class="col">
        <div class="card h-100 shadow-sm cursor-pointer" @click="abrirDetalhes">
            <!-- Área do livro com fundo -->
            <div class="bg-light p-3 d-flex justify-content-center align-items-center">
                <img :src="produto.imagem" class="img-fluid rounded" :alt="produto.titulo" style="max-height: 250px; object-fit: contain;" />
            </div>
            <!-- Faixa com editora -->
<!--            <div class="bg-dark text-white text-center">{{ produto.editora }}</div>-->
            <div class="bg-dark bg-gradient bg-opacity-50 text-white text-center small py-0">{{ produto.editora }}</div>
            <!-- Conteúdo -->
            <div class="card-body d-flex flex-column">
                <h5 class="card-title text-center">{{ produto.titulo }}</h5>
                <p class="card-text text-muted text-center mb-1">Autor: {{ produto.autor }}</p>
                <p class="fw-bold fs-5 text-success text-center">R$ {{ produto.preco.toFixed(2) }}</p>
                <div class="mt-auto text-center">
                    <button class="btn btn-dark bg-gradient" @click.stop="adicionarAoCarrinho(produto)">
                        <i class="bi bi-cart-plus me-2"></i> Adicionar ao Carrinho
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";

interface Produto {
    id: number
    titulo: string
    autor: string
    preco: number
    imagem: string
    editora: string
    anoAtualizacao: number
}

const props = defineProps<{ produto: Produto }>();
const emit = defineEmits<{ (e: "adicionar-carrinho", produto: Produto): void }>();

const router = useRouter();

function adicionarAoCarrinho(produto: Produto) {
    emit("adicionar-carrinho", produto);
}

// Redireciona para a página de detalhes
function abrirDetalhes() {
    router.push({ name: "ProdutoDetalhes", params: { id: props.produto.id } });
}
</script>

<style scoped>
.cursor-pointer {
    cursor: pointer;
}
</style>

<template>
    <Navbar />
    <div class="container my-5">
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4">
            <Produto
                v-for="p in produtos"
                :key="p.id"
                :produto="p"
                @adicionar-carrinho="handleAdicionarCarrinho"
            />
        </div>
    </div>
</template>

<script setup lang="ts">
import Navbar from "@/components/Navbar.vue";
import Produto from "@/components/Produto.vue";

interface Produto {
    id: number
    titulo: string
    autor: string
    preco: number
    imagem: string
    editora: string
    anoAtualizacao: number
}

const produtos: Produto[] = [
    // Mantendo os dois primeiros
    {
        id: 1,
        titulo: "Clean Code",
        autor: "Robert C. Martin",
        preco: 120.0,
        imagem: "https://m.media-amazon.com/images/I/41xShlnTZTL._SX374_BO1,204,203,200_.jpg",
        editora: "Prentice Hall",
        anoAtualizacao: 2020
    },
    {
        id: 2,
        titulo: "Use a Cabeça! Java",
        autor: "Kathy Sierra, Bert Bates",
        preco: 129.9,
        imagem: "https://m.media-amazon.com/images/I/610D1O8WWOL._SY425_BO1,204,203,200_.jpg",
        editora: "Alta Books",
        anoAtualizacao: 2015
    },
    // Novos livros de DDD, arquitetura e desenvolvimento
    {
        id: 3,
        titulo: "Domain-Driven Design",
        autor: "Eric Evans",
        preco: 149.9,
        imagem: "https://m.media-amazon.com/images/I/61aIS4n2jZL._SY466_BO1,204,203,200_.jpg",
        editora: "Addison-Wesley",
        anoAtualizacao: 2016
    },
    {
        id: 4,
        titulo: "Clean Architecture",
        autor: "Robert C. Martin",
        preco: 139.9,
        imagem: "https://m.media-amazon.com/images/I/41-sN-mzwKL._SX374_BO1,204,203,200_.jpg",
        editora: "Prentice Hall",
        anoAtualizacao: 2017
    },
    // Novos 4 livros pedidos
    {
        id: 5,
        titulo: "The Pragmatic Programmer",
        autor: "Andrew Hunt, David Thomas",
        preco: 99.9,
        imagem: "https://m.media-amazon.com/images/I/41as+WafrFL._SX380_BO1,204,203,200_.jpg",
        editora: "Addison-Wesley",
        anoAtualizacao: 2019
    },
    {
        id: 6,
        titulo: "Introdução à Linguagem SQL",
        autor: "Thomas Nield",
        preco: 139.9,
        imagem: "https://m.media-amazon.com/images/I/711siL1zU1L._SY425_BO1,204,203,200_.jpg",
        editora: "Novatec",
        anoAtualizacao: 2016
    },
    {
        id: 7,
        titulo: "Use a Cabeça!: Programação JavaScript",
        autor: "Eric FreemanEric Freeman",
        preco: 109.9,
        imagem: "https://m.media-amazon.com/images/I/71ytnrVAjaL._SY466_BO1,204,203,200_.jpg",
        editora: "Alta Books",
        anoAtualizacao: 2016
    },
    {
        id: 8,
        titulo: "Orientação a Objetos e SOLID Para Ninjas",
        autor: "Mauricio Aniche",
        preco: 99.9,
        imagem: "https://m.media-amazon.com/images/I/61N84VgZ4wL._SY466_.jpg",
        editora: "Casa do Código",
        anoAtualizacao: 2015
    }
];

function handleAdicionarCarrinho(produto: Produto) {
    console.log("Adicionado ao carrinho:", produto);
}
</script>
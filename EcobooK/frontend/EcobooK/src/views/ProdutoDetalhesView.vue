<template>
    <!-- Navbar -->
    <Navbar />

    <div class="container my-5 text-dark">
        <div class="row g-4 justify-content-center">

            <!-- Imagem do livro -->
            <div class="col-md-4 text-center">
                <img
                    :src="livro.imagem"
                    :alt="livro.titulo"
                    class="img-fluid rounded shadow"
                    style="max-height: 450px; object-fit: contain;"
                />
            </div>

            <!-- Informações -->
            <div class="col-md-6">
                <h2 class="fw-bold">{{ livro.titulo }}</h2>
                <p class="text-muted">por {{ livro.autor }}</p>

                <p class="mt-3">
                    <strong>Editora:</strong> {{ livro.editora }} <br />
                    <strong>Ano:</strong> {{ livro.ano }} <br />
                    <strong>Categoria:</strong> {{ livro.categoria }} <br />
                    <strong>Páginas:</strong> {{ livro.paginas }} <br />
                    <strong>ISBN:</strong> {{ livro.isbn }}
                </p>

                <p class="mt-4">
                    <span class="fs-4 fw-bold">R$ {{ livro.preco.toFixed(2) }}</span>
                </p>

                <!-- Seleção de quantidade -->
                <div class="d-flex align-items-center gap-3 mt-3">
                    <label for="quantidade" class="fw-bold">Quantidade:</label>
                    <input
                        id="quantidade"
                        type="number"
                        class="form-control border-dark"
                        style="width: 90px;"
                        min="1"
                        v-model.number="quantidade"
                    />
                </div>

                <!-- Botão de adicionar -->
                <button
                    class="btn btn-dark bg-gradient fw-bold mt-4 px-4 py-2"
                    @click="adicionarAoCarrinho"
                >
                    Adicionar ao Carrinho
                </button>
            </div>
        </div>

        <!-- Sinopse -->
        <div class="row mt-5">
            <div class="col-md-10 mx-auto">
                <h4 class="fw-bold mb-3">Sinopse</h4>
                <p class="text-muted">{{ livro.sinopse }}</p>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import Navbar from "@/components/Navbar.vue";

// Exemplo de dados do livro (isso pode vir da API)
const livro = {
    titulo: "O Senhor dos Anéis - A Sociedade do Anel",
    autor: "J. R. R. Tolkien",
    editora: "HarperCollins",
    ano: 2020,
    categoria: "Fantasia",
    paginas: 576,
    isbn: "978-6555111446",
    preco: 79.90,
    imagem: "https://m.media-amazon.com/images/I/81t2CVWEsUL.jpg",
    sinopse: "A primeira parte da maior obra de fantasia de todos os tempos..."
};

// Quantidade selecionada
const quantidade = ref(1);

// Ação de adicionar ao carrinho
const adicionarAoCarrinho = () => {
    alert(`Adicionado ${quantidade.value}x "${livro.titulo}" ao carrinho!`);
};
</script>

<style scoped>
body {
    background-color: #fff; /* fundo branco */
    color: #000; /* texto preto */
}
</style>

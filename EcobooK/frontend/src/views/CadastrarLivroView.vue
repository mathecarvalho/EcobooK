<template>
    <div class="container my-5">
        <div class="card shadow-lg p-4 rounded-4">
            <h2 class="mb-4 text-center">Cadastro de Livro</h2>

            <form @submit.prevent="salvarLivro">
                <div class="row g-3">
                    <!-- Título -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Título *</label>
                        <input type="text" v-model="form.titulo" class="form-control" required />
                    </div>

                    <!-- Autor -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Autor *</label>
                        <input type="text" v-model="form.autor" class="form-control" required />
                    </div>

                    <!-- Categoria -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Categoria *</label>
                        <input type="text" v-model="form.categoria" class="form-control" required />
                    </div>

                    <!-- Editora -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Editora *</label>
                        <input type="text" v-model="form.editora" class="form-control" required />
                    </div>

                    <!-- Edição -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">Edição *</label>
                        <input type="text" v-model="form.edicao" class="form-control" required />
                    </div>

                    <!-- Ano -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">Ano *</label>
                        <input type="number" v-model="form.anoAtualizacao" class="form-control" required />
                    </div>

                    <!-- ISBN -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">ISBN *</label>
                        <input type="text" v-model="form.isbn" class="form-control" required />
                    </div>

                    <!-- Número de Páginas -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">Número de Páginas *</label>
                        <input type="number" v-model="form.numeroPaginas" class="form-control" required />
                    </div>

                    <!-- Preço -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">Preço (R$) *</label>
                        <input type="number" v-model="form.preco" class="form-control" step="0.01" required />
                    </div>

                    <!-- Grupo de Precificação -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">Grupo de Precificação *</label>
                        <input type="text" v-model="form.grupoPrecificacao" class="form-control" required />
                    </div>

                    <!-- Código de Barras -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Código de Barras *</label>
                        <input type="text" v-model="form.codigoBarras" class="form-control" required />
                    </div>

                    <!-- Imagem URL -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">URL da Imagem *</label>
                        <input type="url" v-model="form.imagem" class="form-control" required />
                    </div>

                    <!-- Dimensões -->
                    <div class="col-md-3">
                        <label class="form-label fw-bold">Altura (cm) *</label>
                        <input type="number" v-model="form.dimensoes.altura" class="form-control" step="0.1" required />
                    </div>
                    <div class="col-md-3">
                        <label class="form-label fw-bold">Largura (cm) *</label>
                        <input type="number" v-model="form.dimensoes.largura" class="form-control" step="0.1" required />
                    </div>
                    <div class="col-md-3">
                        <label class="form-label fw-bold">Profundidade (cm) *</label>
                        <input type="number" v-model="form.dimensoes.profundidade" class="form-control" step="0.1" required />
                    </div>
                    <div class="col-md-3">
                        <label class="form-label fw-bold">Peso (kg) *</label>
                        <input type="number" v-model="form.dimensoes.peso" class="form-control" step="0.01" required />
                    </div>

                    <!-- Sinopse -->
                    <div class="col-12">
                        <label class="form-label fw-bold">Sinopse *</label>
                        <textarea v-model="form.sinopse" class="form-control" rows="5" required></textarea>
                    </div>
                </div>

                <div class="mt-4 d-flex justify-content-end">
                    <button type="submit" class="btn btn-dark btn-lg">
                        <i class="bi bi-save me-2"></i> Salvar Livro
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'

interface Dimensoes {
    altura: number
    largura: number
    profundidade: number
    peso: number
}

interface Produto {
    titulo: string
    autor: string
    categoria: string
    preco: number
    imagem: string
    editora: string
    edicao: string
    anoAtualizacao: number
    isbn: string
    numeroPaginas: number
    sinopse: string
    dimensoes: Dimensoes
    grupoPrecificacao: string
    codigoBarras: string
}

const form = reactive<Produto>({
    titulo: '',
    autor: '',
    categoria: '',
    preco: 0,
    imagem: '',
    editora: '',
    edicao: '',
    anoAtualizacao: new Date().getFullYear(),
    isbn: '',
    numeroPaginas: 0,
    sinopse: '',
    dimensoes: { altura: 0, largura: 0, profundidade: 0, peso: 0 },
    grupoPrecificacao: '',
    codigoBarras: ''
})

function salvarLivro() {
    console.log("Livro cadastrado:", { ...form })
    alert('Livro cadastrado com sucesso!')
}
</script>


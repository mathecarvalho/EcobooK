<template>
    <div class="container my-5">
        <div class="position-absolute top-0 start-0 p-3">
            <h2 class="fw-bold text-dark">EcobooK</h2>
        </div>

        <div class="card shadow-lg p-4 rounded-4">
            <h2 class="mb-4 text-center">Cadastro de Livro</h2>

            <form @submit.prevent="salvarLivro">
                <div class="row g-3">
                    <!-- Título -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Título <span class="text-danger">*</span></label>
                        <input type="text" v-model="form.titulo" class="form-control" required />
                    </div>

                    <!-- Autor -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Autor <span class="text-danger">*</span></label>
                        <input type="text" v-model="form.autor" class="form-control" required />
                    </div>

                    <!-- Categoria -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Categoria <span class="text-danger">*</span></label>
                        <input type="text" v-model="form.categoria" class="form-control" required />
                    </div>

                    <!-- Editora -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Editora <span class="text-danger">*</span></label>
                        <input type="text" v-model="form.editora" class="form-control" required />
                    </div>

                    <!-- Edição -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">Edição <span class="text-danger">*</span></label>
                        <input type="text" v-model="form.edicao" class="form-control" required />
                    </div>

                    <!-- Ano -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">Ano <span class="text-danger">*</span></label>
                        <input type="number" v-model="form.anoAtualizacao" class="form-control" required />
                    </div>

                    <!-- ISBN -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">ISBN <span class="text-danger">*</span></label>
                        <input type="text" v-model="form.isbn" class="form-control" required />
                    </div>

                    <!-- Número de Páginas -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">Número de Páginas <span class="text-danger">*</span></label>
                        <input type="number" v-model="form.numeroPaginas" class="form-control" required />
                    </div>

                    <!-- Preço -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">Preço (R$) <span class="text-danger">*</span></label>
                        <input type="number" v-model="form.preco" class="form-control" step="0.01" required />
                    </div>

                    <!-- Grupo de Precificação -->
                    <div class="col-md-4">
                        <label class="form-label fw-bold">Grupo de Precificação <span class="text-danger">*</span></label>
                        <input type="text" v-model="form.grupoPrecificacao" class="form-control" required />
                    </div>

                    <!-- Código de Barras -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">Código de Barras <span class="text-danger">*</span></label>
                        <input type="text" v-model="form.codigoBarras" class="form-control" required />
                    </div>

                    <!-- Imagem URL -->
                    <div class="col-md-6">
                        <label class="form-label fw-bold">URL da Imagem <span class="text-danger">*</span></label>
                        <input type="url" v-model="form.imagem" class="form-control" required />
                    </div>

                    <!-- Dimensões -->
                    <div class="col-md-3">
                        <label class="form-label fw-bold">Altura (cm) <span class="text-danger">*</span></label>
                        <input type="number" v-model="form.dimensoes.altura" class="form-control" step="0.1" required />
                    </div>
                    <div class="col-md-3">
                        <label class="form-label fw-bold">Largura (cm) <span class="text-danger">*</span></label>
                        <input type="number" v-model="form.dimensoes.largura" class="form-control" step="0.1" required />
                    </div>
                    <div class="col-md-3">
                        <label class="form-label fw-bold">Profundidade (cm) <span class="text-danger">*</span></label>
                        <input type="number" v-model="form.dimensoes.profundidade" class="form-control" step="0.1" required />
                    </div>
                    <div class="col-md-3">
                        <label class="form-label fw-bold">Peso (kg) <span class="text-danger">*</span></label>
                        <input type="number" v-model="form.dimensoes.peso" class="form-control" step="0.01" required />
                    </div>

                    <!-- Sinopse -->
                    <div class="col-12">
                        <label class="form-label fw-bold">Sinopse <span class="text-danger">*</span></label>
                        <textarea v-model="form.sinopse" class="form-control" rows="5" required></textarea>
                    </div>

                    <!-- Quantidade em Estoque -->
                    <div class="col-md-6 mt-3">
                        <label class="form-label fw-bold">Quantidade em Estoque <span class="text-danger">*</span></label>
                        <input type="number" v-model="form.estoque" class="form-control" min="0" required />
                    </div>

                    <!-- Status -->
                    <div class="col-md-6 mt-3">
                        <label class="form-label fw-bold">Status <span class="text-danger">*</span></label>
                        <select v-model="form.status" class="form-select" required>
                            <option value="ativo">Ativo</option>
                            <option value="inativo">Inativo</option>
                        </select>
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
    estoque: number
    status: 'ativo' | 'inativo'
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
    codigoBarras: '',
    estoque: 0,
    status: 'ativo'
})

function salvarLivro() {
    console.log("Livro cadastrado:", { ...form })
    alert('Livro cadastrado com sucesso!')
}
</script>


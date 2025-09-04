<template>
    <div class="wrapper">
        <!-- Marca no canto superior esquerdo -->
        <div class="position-absolute top-0 start-0 p-3">
            <h2 class="fw-bold text-dark">EcobooK</h2>
        </div>
        <div class="container d-flex justify-content-center align-items-center min-vh-100">
            <div class="card shadow rounded-3 p-4 m-4" style="max-width: 800px; width: 100%;">
                <h3 class="text-center mb-4">Cadastro de Cliente</h3>

                <form @submit.prevent="handleSubmit">
                    <!-- Dados pessoais -->
                    <h5>Dados Pessoais</h5>
                    <div class="row g-3">
                        <div class="col-md-6">
                            <input type="text" class="form-control" placeholder="Nome" v-model="form.nome" required>
                        </div>
                        <div class="col-md-6">
                            <input type="text" class="form-control" placeholder="Sobrenome" v-model="form.sobrenome" required>
                        </div>
                        <div class="col-md-12">
                            <input type="email" class="form-control" placeholder="E-mail" v-model="form.email" required>
                        </div>
                        <div class="col-md-12">
                            <input type="tel" class="form-control" placeholder="Celular" v-model="form.celular" @input="maskPhone" required>
                        </div>
                        <div class="col-md-12">
                            <input type="text" class="form-control" placeholder="CPF" v-model="form.cpf" @input="maskCPF" required>
                        </div>
                    </div>

                    <hr>

                    <!-- Acesso -->
                    <h5>Acesso</h5>
                    <div class="row g-3">
                        <div class="col-md-6">
                            <input type="password" class="form-control" placeholder="Senha" v-model="form.senha" required>
                        </div>
                        <div class="col-md-6">
                            <input type="password" class="form-control" placeholder="Confirmar senha" v-model="form.confirmarSenha" required>
                        </div>
                    </div>

                    <hr>

                    <!-- Endereços -->
                    <h5>Endereços</h5>
                    <div v-for="(endereco, index) in form.enderecos" :key="index">
                        <div class="card mb-3">
                            <div class="card-header">
                                <div class="d-flex align-items-center justify-content-between">
                                    <h6>Endereço {{ index + 1 }}</h6>
                                    <button class="btn btn-danger btn-sm btn-remove p-1 w-auto" type="button" @click="removerEndereco(index)"><i class="bi bi-x-lg"></i></button>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="row g-3">
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" placeholder="CEP" v-model="endereco.cep" @input="maskCEP($event, index)" required>
                                    </div>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" placeholder="Rua" v-model="endereco.rua" required>
                                    </div>
                                    <div class="col-md-4">
                                        <input type="text" class="form-control" placeholder="Número" v-model="endereco.numero" required>
                                    </div>
                                    <div class="col-md-8">
                                        <input type="text" class="form-control" placeholder="Complemento" v-model="endereco.complemento">
                                    </div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control" placeholder="Bairro" v-model="endereco.bairro" required>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control" placeholder="Cidade" v-model="endereco.cidade" required>
                                    </div>
                                    <div class="col-md-12">
                                        <input type="text" class="form-control" placeholder="Estado" v-model="endereco.estado" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-secondary w-100 mb-3" type="button" @click="adicionarEndereco">+ Adicionar Endereço</button>

                    <hr>

                    <!-- Cartões -->
                    <h5>Cartões</h5>
                    <div v-for="(cartao, index) in form.cartoes" :key="index">
                        <div class="card mb-3">
                            <div class="card-header">
                                <div class="d-flex align-items-center justify-content-between">
                                    <h6>Cartão {{ index + 1 }}</h6>
                                    <button class="btn btn-danger btn-sm btn-remove p-1 w-auto" type="button" @click="removerCartao(index)"><i class="bi bi-x-lg"></i></button>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="row g-3">
                                    <div class="col-md-12">
                                        <input type="text" class="form-control" placeholder="Número do Cartão" v-model="cartao.numero" @input="maskCard($event, index)" required>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control" placeholder="Validade (MM/AA)" v-model="cartao.validade" @input="maskValidade($event, index)" required>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control" placeholder="CVV" v-model="cartao.cvv" @input="maskCVV($event, index)" required>
                                    </div>
                                    <div class="col-md-12">
                                        <input type="text" class="form-control" placeholder="Nome no Cartão" v-model="cartao.nomeTitular" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-secondary w-100 mb-3" type="button" @click="adicionarCartao">+ Adicionar Cartão</button>

                    <hr>

                    <h5>Termo</h5>
                    <div class="form-check mt-3">
                        <input class="form-check-input" type="checkbox" id="termos" v-model="form.termos" required>
                        <label class="form-check-label" for="termos">
                            Aceito os <a href="#">termos de uso</a> e <a href="#">política de privacidade</a>.
                        </label>
                    </div>

                    <button class="btn btn-primary w-100 mt-4" type="submit">Cadastrar</button>
                    <a href="/login" class="btn btn-link w-100 mt-2">Já tenho conta</a>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'

const form = reactive({
    nome: '',
    sobrenome: '',
    email: '',
    celular: '',
    cpf: '',
    senha: '',
    confirmarSenha: '',
    enderecos: [
        { cep: '', rua: '', numero: '', complemento: '', bairro: '', cidade: '', estado: '' }
    ],
    cartoes: [
        { numero: '', validade: '', cvv: '', nomeTitular: '' }
    ],
    termos: false
})

function adicionarEndereco() {
    form.enderecos.push({ cep: '', rua: '', numero: '', complemento: '', bairro: '', cidade: '', estado: '' })
}
function removerEndereco(index) {
    form.enderecos.splice(index, 1)
}

function adicionarCartao() {
    form.cartoes.push({ numero: '', validade: '', cvv: '', nomeTitular: '' })
}
function removerCartao(index) {
    form.cartoes.splice(index, 1)
}

function maskPhone(event) {
    let value = event.target.value.replace(/\D/g, '')
    if (value.length > 10) {
        value = value.slice(0, 11)
        value = value.replace(/(\d{2})(\d)/, '($1) $2')
        value = value.replace(/(\d{5})(\d)/, '$1-$2')
    } else {
        value = value.slice(0, 10)
        value = value.replace(/(\d{2})(\d)/, '($1) $2')
        value = value.replace(/(\d{4})(\d)/, '$1-$2')
    }
    form.celular = value
}

function maskCPF(event) {
    let value = event.target.value.replace(/\D/g, '')
    value = value.slice(0, 11)
    value = value.replace(/(\d{3})(\d)/, '$1.$2')
    value = value.replace(/(\d{3})(\d)/, '$1.$2')
    value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2')
    form.cpf = value
}

function maskCEP(event, index) {
    let value = event.target.value.replace(/\D/g, '')
    value = value.slice(0, 8)
    value = value.replace(/(\d{5})(\d)/, '$1-$2')
    form.enderecos[index].cep = value
}

function maskCard(event, index) {
    let value = event.target.value.replace(/\D/g, '')
    value = value.slice(0, 16)
    value = value.replace(/(\d{4})(?=\d)/g, '$1 ')
    form.cartoes[index].numero = value
}

function maskValidade(event, index) {
    let value = event.target.value.replace(/\D/g, '')
    value = value.slice(0, 4)
    value = value.replace(/(\d{2})(\d)/, '$1/$2')
    form.cartoes[index].validade = value
}

function maskCVV(event, index) {
    let value = event.target.value.replace(/\D/g, '')
    form.cartoes[index].cvv = value.slice(0, 3)
}

function handleSubmit() {
    if (form.senha !== form.confirmarSenha) {
        alert('As senhas não coincidem!')
        return
    }
    console.log('Formulário enviado:', { ...form })
    alert('Cadastro realizado com sucesso!')
}
</script>

<style scoped>
.wrapper {
    background-color: #f8f8f8;
}

h1 {
    text-align: center;
    margin-bottom: 1.5rem;
}

label {
    margin-bottom: 0.5rem;
    font-weight: 600;
}

input {
    padding: 0.5rem;
    font-size: 1rem;
    border-radius: 4px;
    border: 1px solid #ccc;
}

button {
    width: 100%;
    padding: 0.7rem;
    background-color: #222;
    color: white;
    border: none;
    border-radius: 4px;
    font-weight: 700;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: #444;
}

.btn-remove {
    background: red;
    color: white;
    border: none;
}

.btn-remove:hover {
    background: transparent;
    color: red;
}
</style>
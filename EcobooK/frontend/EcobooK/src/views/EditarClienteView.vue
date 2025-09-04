<template>
    <div class="wrapper">
        <div class="container d-flex justify-content-center align-items-center min-vh-100">
            <div class="card shadow rounded-3 p-4 m-4" style="max-width: 800px; width: 100%;">
                <h3 class="text-center mb-4">Alterar Dados do Cliente</h3>

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
                            <input type="password" class="form-control" placeholder="Nova Senha (opcional)" v-model="form.senha">
                        </div>
                        <div class="col-md-6">
                            <input type="password" class="form-control" placeholder="Confirmar nova senha" v-model="form.confirmarSenha">
                        </div>
                    </div>

                    <hr>

                    <!-- Endereços -->
                    <h5>Endereços</h5>
                    <div v-for="(endereco, index) in form.enderecos" :key="index" class="border rounded p-3 mb-3">
                        <h6>Endereço {{ index + 1 }}</h6>
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
                        <button class="btn btn-danger btn-sm mt-2" type="button" @click="removerEndereco(index)">Remover</button>
                    </div>
                    <button class="btn btn-dark w-100 mb-3" type="button" @click="adicionarEndereco">+ Adicionar Endereço</button>

                    <hr>

                    <!-- Cartões -->
                    <h5>Cartões</h5>
                    <div v-for="(cartao, index) in form.cartoes" :key="index" class="border rounded p-3 mb-3">
                        <h6>Cartão {{ index + 1 }}</h6>
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
                        <button class="btn btn-danger btn-sm mt-2" type="button" @click="removerCartao(index)">Remover</button>
                    </div>
                    <button class="btn btn-dark w-100 mb-3" type="button" @click="adicionarCartao">+ Adicionar Cartão</button>

                    <button class="btn btn-dark w-100 mt-4" type="submit">Salvar Alterações</button>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup>
import { reactive } from 'vue'

// Aqui você carregaria os dados do cliente do backend
const form = reactive({
    nome: 'João',
    sobrenome: 'Silva',
    email: 'joao@email.com',
    celular: '(11) 99999-9999',
    cpf: '123.456.789-00',
    senha: '',
    confirmarSenha: '',
    enderecos: [
        { cep: '01001-000', rua: 'Rua Exemplo', numero: '123', complemento: '', bairro: 'Centro', cidade: 'São Paulo', estado: 'SP' }
    ],
    cartoes: [
        { numero: '1234 5678 9012 3456', validade: '12/28', cvv: '123', nomeTitular: 'JOAO SILVA' }
    ]
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

function maskPhone(event) { /* ... igual cadastro ... */ }
function maskCPF(event) { /* ... igual cadastro ... */ }
function maskCEP(event, index) { /* ... igual cadastro ... */ }
function maskCard(event, index) { /* ... igual cadastro ... */ }
function maskValidade(event, index) { /* ... igual cadastro ... */ }
function maskCVV(event, index) { /* ... igual cadastro ... */ }

function handleSubmit() {
    if (form.senha && form.senha !== form.confirmarSenha) {
        alert('As senhas não coincidem!')
        return
    }
    console.log('Alterações salvas:', { ...form })
    alert('Dados atualizados com sucesso!')
}
</script>


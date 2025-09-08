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
                        <div class="col-md-6">
                            <input type="date" class="form-control" v-model="form.dataNascimento" required>
                        </div>
                        <div class="col-md-6">
                            <select class="form-control" v-model="form.genero" required>
                                <option value="">Selecione o gênero</option>
                                <option value="MASCULINO">Masculino</option>
                                <option value="FEMININO">Feminino</option>
                                <option value="OUTRO">Outro</option>
                            </select>
                        </div>
                        <div class="col-md-4">
                            <input type="text" class="form-control" placeholder="DDD" v-model="form.telefone.ddd" required>
                        </div>
                        <div class="col-md-8">
                            <input type="tel" class="form-control" placeholder="Número" v-model="form.telefone.numero" required>
                        </div>
                        <div class="col-md-12">
                            <input type="text" class="form-control" placeholder="CPF" v-model="form.cpf" required>
                        </div>
                    </div>

                    <hr>

                    <!-- Endereços -->
                    <h5>Endereços</h5>
                    <div v-for="(endereco, index) in form.enderecos" :key="index" class="border rounded p-3 mb-3">
                        <h6>Endereço {{ index + 1 }}</h6>
                        <div class="row g-3">
                            <div class="col-md-6">
                                <input type="text" class="form-control" placeholder="Nome (Casa, Trabalho...)" v-model="endereco.nome" required>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" placeholder="Tipo de Residência" v-model="endereco.tipoResidencia" required>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" placeholder="Tipo de Logradouro" v-model="endereco.tipoLogradouro" required>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" placeholder="Logradouro" v-model="endereco.logradouro" required>
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
                                <input type="text" class="form-control" placeholder="CEP" v-model="endereco.cep" required>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" placeholder="Cidade" v-model="endereco.cidade" required>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" placeholder="Estado" v-model="endereco.estado" required>
                            </div>
                            <div class="col-md-12">
                                <input type="text" class="form-control" placeholder="País" v-model="endereco.pais" required>
                            </div>
                        </div>
                        <button v-if="index > 0" class="btn btn-danger btn-sm mt-2" type="button" @click="removerEndereco(index)">Remover</button>
                    </div>
                    <button class="btn btn-dark w-100 mb-3" type="button" @click="adicionarEndereco">+ Adicionar Endereço</button>

                    <hr>

                    <!-- Cartões -->
                    <h5>Cartões</h5>
                    <div v-for="(cartao, index) in form.cartoes" :key="index" class="border rounded p-3 mb-3">
                        <h6>Cartão {{ index + 1 }}</h6>
                        <div class="row g-3">
                            <div class="col-md-12">
                                <input type="text" class="form-control" placeholder="Número do Cartão" v-model="cartao.numero" required>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" placeholder="Validade (MM/AA)" v-model="cartao.validade" required>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control" placeholder="Código de Segurança" v-model="cartao.codigoSeguranca" required>
                            </div>
                            <div class="col-md-12">
                                <input type="text" class="form-control" placeholder="Nome Impresso" v-model="cartao.nomeImpresso" required>
                            </div>
                            <div class="col-md-12">
                                <input type="text" class="form-control" placeholder="Bandeira" v-model="cartao.bandeira" required>
                            </div>
                        </div>
                        <button v-if="index > 0" class="btn btn-danger btn-sm mt-2" type="button" @click="removerCartao(index)">Remover</button>
                    </div>
                    <button class="btn btn-dark w-100 mb-3" type="button" @click="adicionarCartao">+ Adicionar Cartão</button>

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

                    <button class="btn btn-dark w-100 mt-4" type="submit">Salvar Alterações</button>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { reactive, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import api from "@/service/api";

interface Endereco {
    nome: string
    tipoResidencia: string
    tipoLogradouro: string
    logradouro: string
    numero: string
    complemento?: string
    bairro: string
    cep: string
    cidade: string
    estado: string
    pais: string
    observacoes?: string
}

interface Cartao {
    numero: string
    nomeImpresso: string
    validade: string
    codigoSeguranca: string
    bandeira: string
}

interface Telefone {
    tipo: string
    ddd: string
    numero: string
}

const route = useRoute()
const id = route.params.id

const form = reactive({
    nome: "",
    sobrenome: "",
    email: "",
    dataNascimento: "",
    genero: "",
    telefone: { tipo: "Celular", ddd: "", numero: "" } as Telefone,
    cpf: "",
    senha: "",
    confirmarSenha: "",
    enderecos: [] as Endereco[],
    cartoes: [] as Cartao[],
})

function adicionarEndereco() {
    form.enderecos.push({
        nome: "",
        tipoResidencia: "Casa",
        tipoLogradouro: "Rua",
        logradouro: "",
        numero: "",
        complemento: "",
        bairro: "",
        cep: "",
        cidade: "",
        estado: "",
        pais: "Brasil",
        observacoes: ""
    })
}
function removerEndereco(index: number) {
    form.enderecos.splice(index, 1)
}

function adicionarCartao() {
    form.cartoes.push({
        numero: "",
        nomeImpresso: "",
        validade: "",
        codigoSeguranca: "",
        bandeira: "VISA"
    })
}
function removerCartao(index: number) {
    form.cartoes.splice(index, 1)
}

async function carregarDados() {
    const { data } = await api.get(`/cliente/detalhes/${route.params.id}`)
    Object.assign(form, data)
    console.log("Dados recebidos:", data)
}

async function handleSubmit() {
    if (form.senha && form.senha !== form.confirmarSenha) {
        alert('As senhas não coincidem!')
        return
    }
    await api.put(`/cliente/alterar/${id}`, form)
    alert('Dados atualizados com sucesso!')
}

onMounted(() => {
    carregarDados()
})
</script>

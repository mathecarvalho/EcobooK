<template>
    <div class="wrapper">
        <!-- Marca no canto superior esquerdo -->
        <div class="position-absolute top-0 start-0 p-5">
            <h2 class="fw-bold">EcobooK</h2>
        </div>

        <div class="container d-flex justify-content-center align-items-center min-vh-100">
            <div class="card shadow-lg rounded-4 p-4 m-4 w-100" style="max-width: 800px;">

                <!-- Barra de progresso -->
                <div class="progress mb-4" style="height: 8px;">
                    <div
                        class="progress-bar bg-dark"
                        role="progressbar"
                        :style="{ width: progressWidth }">
                    </div>
                </div>

                <h3 class="text-center mb-4 fw-bold text-dark">
                    <i class="bi bi-person-circle me-2"></i>
                    Cadastro de Cliente
                </h3>

                <form ref="formRef" class="needs-validation" @submit.prevent="handleSubmit" novalidate>

                <!-- Etapa 1: Dados Pessoais -->
                    <!-- Etapa 1: Dados Pessoais -->
                    <section v-if="step === 1">
                        <h5 class="mb-3"><i class="bi bi-person-badge me-2"></i>Dados Pessoais</h5>
                        <div class="row g-3">
                            <div class="col-md-6">
                                <input type="text" class="form-control shadow-sm" placeholder="Nome" v-model="form.nome" required>
                            </div>
                            <div class="col-md-6">
                                <input type="text" class="form-control shadow-sm" placeholder="Sobrenome" v-model="form.sobrenome" required>
                            </div>
                            <!-- CPF -->
                            <div class="col-md-12">
                                <input type="text" class="form-control shadow-sm" placeholder="CPF" v-model="form.cpf" maxlength="11" required>
                            </div>

                            <!-- Data de Nascimento -->
                            <div class="col-md-6">
                                <div class="d-flex gap-2">
                                    <input type="text"
                                           class="form-control shadow-sm"
                                           placeholder="DD"
                                           v-model="dia"
                                           maxlength="2"
                                           required>
                                    <input type="text"
                                           class="form-control shadow-sm"
                                           placeholder="MM"
                                           v-model="mes"
                                           maxlength="2"
                                           required>
                                    <input type="text"
                                           class="form-control shadow-sm"
                                           placeholder="AAAA"
                                           v-model="ano"
                                           maxlength="4"
                                           required>
                                </div>
                            </div>

                            <!-- Gênero -->
                            <div class="col-md-6">
                                <select class="form-select shadow-sm" v-model="form.genero" required>
                                    <option disabled value="">Selecione o gênero</option>
                                    <option value="MASCULINO">Masculino</option>
                                    <option value="FEMININO">Feminino</option>
                                    <option value="OUTRO">Outro</option>
                                </select>
                            </div>

                            <!-- Telefone -->
                            <div class="col-md-3">
                                <select class="form-select shadow-sm" v-model="form.telefone.tipo">
                                    <option value="Celular">Celular</option>
                                    <option value="Residencial">Residencial</option>
                                    <option value="Comercial">Comercial</option>
                                </select>
                            </div>
                            <div class="col-md-2">
                                <input type="text" class="form-control shadow-sm" placeholder="DDD" v-model="form.telefone.ddd" maxlength="2" required>
                            </div>
                            <div class="col-md-7">
                                <input type="text"
                                       class="form-control shadow-sm"
                                       placeholder="Número"
                                       v-model="form.telefone.numero"
                                       maxlength="9"
                                       required>
                            </div>
                        </div>
                    </section>

                    <!-- Etapa 2: Endereços -->
                    <section v-if="step === 2">
                        <h5 class="mb-3"><i class="bi bi-geo-alt me-2"></i>Endereços</h5>
                        <div v-for="(endereco, index) in form.enderecos" :key="index" class="card border-0 shadow-sm mb-3">
                            <div class="card-header bg-light d-flex justify-content-between align-items-center">
                                <h6 class="mb-0"><i class="bi bi-house-door me-2"></i>Endereço {{ index + 1 }}</h6>
                                <button v-if="index > 0" class="btn btn-danger btn-sm" type="button" @click="removerEndereco(index)">
                                    <i class="bi bi-x-lg"></i>
                                </button>
                            </div>

                            <div class="card-body">
                                <div class="row g-3">
                                    <!-- Nome do endereço -->
                                    <div class="col-md-6">
                                        <input type="text" class="form-control shadow-sm" placeholder="Nome do endereço"
                                               v-model="endereco.nome" required>
                                    </div>

                                    <!-- Tipo de residência -->
                                    <div class="col-md-3">
                                        <select class="form-select shadow-sm" v-model="endereco.tipoResidencia" required>
                                            <option disabled value="">Tipo de Residência</option>
                                            <option>Casa</option>
                                            <option>Apartamento</option>
                                            <option>Comercial</option>
                                        </select>
                                    </div>

                                    <!-- Tipo de logradouro -->
                                    <div class="col-md-3">
                                        <select class="form-select shadow-sm" v-model="endereco.tipoLogradouro" required>
                                            <option disabled value="">Tipo de Logradouro</option>
                                            <option>Rua</option>
                                            <option>Avenida</option>
                                            <option>Travessa</option>
                                        </select>
                                    </div>

                                    <!-- Logradouro -->
                                    <div class="col-md-6">
                                        <input type="text" class="form-control shadow-sm" placeholder="Logradouro" v-model="endereco.logradouro" required>
                                    </div>

                                    <!-- Número -->
                                    <div class="col-md-2">
                                        <input type="text" class="form-control shadow-sm" placeholder="Número" v-model="endereco.numero" required>
                                    </div>

                                    <!-- Complemento -->
                                    <div class="col-md-4">
                                        <input type="text" class="form-control shadow-sm" placeholder="Complemento" v-model="endereco.complemento">
                                    </div>

                                    <!-- Bairro -->
                                    <div class="col-md-6">
                                        <input type="text" class="form-control shadow-sm" placeholder="Bairro" v-model="endereco.bairro" required>
                                    </div>

                                    <!-- CEP -->
                                    <div class="col-md-3">
                                        <input type="text" class="form-control shadow-sm" placeholder="CEP" v-model="endereco.cep" @input="maskCEP($event)" required>
                                    </div>

                                    <!-- Cidade -->
                                    <div class="col-md-6">
                                        <input type="text" class="form-control shadow-sm" placeholder="Cidade" v-model="endereco.cidade" required>
                                    </div>

                                    <!-- Estado -->
                                    <div class="col-md-3">
                                        <input type="text" class="form-control shadow-sm" placeholder="Estado" v-model="endereco.estado" required>
                                    </div>

                                    <!-- País -->
                                    <div class="col-md-3">
                                        <input type="text" class="form-control shadow-sm" placeholder="País" v-model="endereco.pais" required>
                                    </div>

                                    <!-- Observações -->
                                    <div class="col-md-12">
                                        <textarea class="form-control shadow-sm" placeholder="Observações" v-model="endereco.observacoes"></textarea>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <button class="btn btn-outline-dark" type="button" @click="adicionarEndereco">
                            <i class="bi bi-plus-circle me-2"></i>Adicionar Endereço
                        </button>
                    </section>

                    <!-- Etapa 3: Cartões -->
                    <section v-if="step === 3">
                        <h5 class="mb-3"><i class="bi bi-credit-card me-2"></i>Cartões</h5>
                        <div v-for="(cartao, index) in form.cartoes" :key="index" class="card border-0 shadow-sm mb-3">
                            <div class="card-header bg-light d-flex justify-content-between align-items-center">
                                <h6 class="mb-0"><i class="bi bi-credit-card-2-front me-2"></i>Cartão {{ index + 1 }}</h6>
                                <button v-if="index > 0" class="btn btn-danger btn-sm" type="button" @click="removerCartao(index)">
                                    <i class="bi bi-x-lg"></i>
                                </button>
                            </div>

                            <div class="card-body">
                                <div class="row g-3">
                                    <div class="col-md-12">
                                        <input type="text" class="form-control shadow-sm" placeholder="Número do Cartão"
                                               v-model="cartao.numero" maxlength="16" required>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control shadow-sm" placeholder="Validade (MM/AA)"
                                               v-model="cartao.validade" @input="maskValidade($event)" required>
                                    </div>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control shadow-sm" placeholder="CVV"
                                               v-model="cartao.codigoSeguranca" @input="maskCVV($event)" required>
                                    </div>
                                    <div class="col-md-12">
                                        <input type="text" class="form-control shadow-sm" placeholder="Nome Impresso no Cartão"
                                               v-model="cartao.nomeImpresso" required>
                                    </div>
                                    <div class="col-md-6">
                                        <select class="form-select shadow-sm" v-model="cartao.bandeira" required>
                                            <option disabled value="">Bandeira</option>
                                            <option>VISA</option>
                                            <option>MasterCard</option>
                                            <option>American Express</option>
                                            <option>Hipercard</option>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <button class="btn btn-outline-dark" type="button" @click="adicionarCartao">
                            <i class="bi bi-plus-circle me-2"></i>Adicionar Cartão
                        </button>
                    </section>

                    <!-- Etapa 4: Acesso -->
                    <section v-if="step === 4">
                        <h5 class="mb-3"><i class="bi bi-shield-lock me-2"></i>Acesso</h5>
                        <div class="row g-3 flex-column">
                            <div class="col">
                                <input type="email" class="form-control shadow-sm" placeholder="E-mail" v-model="form.email" required>
                            </div>
                            <div class="col">
                                <input type="password" class="form-control shadow-sm" placeholder="Senha" v-model="form.senha" required>
                            </div>
                            <div class="col">
                                <input type="password" class="form-control shadow-sm" placeholder="Confirmar senha" v-model="form.confirmarSenha" required>
                            </div>
                        </div>
                    </section>

                    <!-- Etapa 5: Termos -->
                    <section v-if="step === TOTAL_STEPS">
                        <h5 class="mb-3"><i class="bi bi-file-earmark-text me-2"></i>Termo</h5>
                        <div class="form-check mt-3">
                            <input class="form-check-input" type="checkbox" id="termos" v-model="form.termos" required>
                            <label class="form-check-label" for="termos">
                                Aceito os <a href="#">termos de uso</a> e <a href="#">política de privacidade</a>.
                            </label>
                        </div>
                    </section>

                    <!-- Mensagem de erro -->
                    <div v-if="erroMensagem" class="alert alert-danger mt-3">
                        {{ erroMensagem }}
                    </div>

                    <!-- Botões navegação -->
                    <div class="d-flex justify-content-between mt-4">
                        <button
                            v-if="step > 1"
                            type="button"
                            class="btn btn-outline-dark px-4"
                            @click="step--">
                            <i class="bi bi-arrow-left me-2"></i>Voltar
                        </button>

                        <button
                            v-if="step < TOTAL_STEPS"
                            type="button"
                            class="btn btn-dark ms-auto px-4"
                            @click="avancarEtapa">
                            Próximo<i class="bi bi-arrow-right ms-2"></i>
                        </button>

                        <button
                            v-if="step === TOTAL_STEPS"
                            type="submit"
                            class="btn btn-dark ms-auto px-4 fw-bold">
                            <i class="bi bi-check2-circle me-2"></i>Cadastrar
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { reactive, ref, computed, watch } from "vue";
import api from "@/service/api";
import {maskCPF, maskPhone, maskCEP, maskCard, maskValidade, maskCVV, maskDate} from "@/utils/masks";

const formRef = ref<HTMLFormElement | null>(null);
const erroMensagem = ref("");
const TOTAL_STEPS = 5;
const step = ref(1);
const progressWidth = computed(() => `${(step.value / TOTAL_STEPS) * 100}%`);
const dia = ref('');
const mes = ref('');
const ano = ref('');

// Tipos
interface Endereco {
    nome: string;               // Nome do endereço, ex: "Casa", "Trabalho"
    tipoResidencia: string;     // Ex: Casa, Apartamento
    tipoLogradouro: string;     // Rua, Avenida, Travessa
    logradouro: string;         // Nome da rua/avenida
    numero: string;
    complemento?: string;
    bairro: string;
    cep: string;
    cidade: string;
    estado: string;
    pais: string;
    observacoes?: string;       // Observações opcionais
}

interface Cartao {
    numero: string;
    nomeImpresso: string;
    validade: string;
    codigoSeguranca: string;
    bandeira: string;
}

interface Telefone {
    tipo: string;
    ddd: string;
    numero: string;
}

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
    enderecos: [
        {
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
        } as Endereco
    ],
    cartoes: [
        {
            numero: "",
            nomeImpresso: "",
            validade: "",
            codigoSeguranca: "",
            bandeira: "VISA",
        } as Cartao
    ],
    termos: false,
});

watch([dia, mes, ano], ([d, m, a]) => {
    if (d.length === 2 && m.length === 2 && a.length === 4) {
        form.dataNascimento = `${a}-${m}-${d}`
    } else {
        form.dataNascimento = ""
    }
})

function adicionarEndereco() {
    form.enderecos.push({
        tipoResidencia: "Casa",
        tipoLogradouro: "Rua",
        logradouro: "",
        numero: "",
        complemento: "",
        bairro: "",
        cidade: "",
        estado: "",
        cep: "",
        pais: "Brasil",
    } as Endereco);
}
function removerEndereco(index: number) {
    form.enderecos.splice(index, 1);
}

function adicionarCartao() {
    form.cartoes.push({
        numero: "",
        nomeImpresso: "",
        validade: "",
        codigoSeguranca: "",
        bandeira: "VISA",
    });
}
function removerCartao(index: number) {
    form.cartoes.splice(index, 1);
}

function validarSenhas() {
    if (form.senha !== form.confirmarSenha) {
        alert("As senhas não coincidem!");
        return false;
    }
    return true;
}

// Substituir o botão "Próximo" pelo clique validado
function avancarEtapa() {
    if (!formRef.value) return;

    formRef.value.classList.add("was-validated"); // ativa a validação do Bootstrap

    if (formRef.value.checkValidity()) {
        erroMensagem.value = ""; // limpa mensagem
        step.value++;
        formRef.value.classList.remove("was-validated"); // limpa para próxima etapa
    } else {
        erroMensagem.value = "Por favor, preencha todos os campos obrigatórios corretamente!";
    }
}

const handleSubmit = async () => {
    if (!formRef.value?.checkValidity()) {
        erroMensagem.value = "Por favor, preencha todos os campos obrigatórios corretamente!";
        formRef.value.classList.add("was-validated");
        return;
    }

    if (!validarSenhas()) {
        erroMensagem.value = "As senhas não coincidem!";
        return;
    }

    try {
        erroMensagem.value = "";
        await api.post("/cliente/cadastrar", form);
        alert("Cadastro realizado com sucesso!");
    } catch (erro: any) {
        erroMensagem.value = "Erro ao cadastrar cliente!";
        console.error("Erro ao cadastrar:", erro.response?.data || erro.message);
    }
};
</script>

<style scoped>
.wrapper {
    background-color: #f8f8f8;
}

.card {
    border-radius: 1rem;
}

input {
    padding: 0.5rem;
    font-size: 1rem;
}
</style>

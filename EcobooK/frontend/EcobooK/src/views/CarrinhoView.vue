<template>
    <!-- Navbar -->
    <Navbar />

    <div class="container my-5">
        <!-- Título da tela -->
        <h2 class="fw-bold text-center mb-4">Carrinho de Compras</h2>

        <!-- Lista de produtos no carrinho -->
        <div v-if="carrinho.length > 0">
            <!-- Cabeçalho da tabela -->
            <div class="d-none d-md-flex fw-bold border-bottom pb-2 mb-2 text-muted">
                <div class="flex-grow-1">Produto</div>
                <div style="width: 100px;" class="text-center">Qtd</div>
                <div style="width: 120px;" class="text-end">Subtotal</div>
                <div style="width: 80px;" class="text-center"></div>
            </div>

            <!-- Itens do carrinho -->
            <div
                v-for="(item, index) in carrinho"
                :key="index"
                class="d-flex flex-column flex-md-row align-items-center justify-content-between border-bottom py-3"
            >
                <div class="d-flex align-items-center mb-3 mb-md-0 flex-grow-1">
                    <img
                        :src="item.imagem"
                        alt="Capa do livro"
                        class="img-thumbnail me-3"
                        style="width: 80px; height: 100px; object-fit: cover"
                    />
                    <div>
                        <h5 class="mb-1">{{ item.titulo }}</h5>
                        <small class="text-muted">Autor: {{ item.autor }}</small><br />
                        <small class="fw-bold">Preço: R$ {{ item.preco.toFixed(2) }}</small>
                    </div>
                </div>

                <div class="text-center mb-2 mb-md-0" style="width: 100px;">
                    <div class="d-flex justify-content-center align-items-center">
                        <button
                            class="btn btn-sm btn-outline-secondary px-1 py-0"
                            @click="diminuirQuantidade(index)"
                        >
                            −
                        </button>
                        <span class="mx-2">{{ item.quantidade }}</span>
                        <button
                            class="btn btn-sm btn-outline-secondary px-1 py-0"
                            @click="aumentarQuantidade(index)"
                        >
                            +
                        </button>
                    </div>
                </div>

                <div class="text-end fw-bold text-success mb-2 mb-md-0" style="width: 120px;">
                    R$ {{ (item.preco * item.quantidade).toFixed(2) }}
                </div>

                <div class="text-center" style="width: 80px;">
                    <button
                        class="btn btn-sm btn-link text-muted p-0 fw-bold fs-5"
                        @click="removerItem(index)"
                    >
                        ×
                    </button>
                </div>
            </div>

            <!-- Seção de Frete -->
            <div class="mt-4 p-3 border rounded">
                <h5 class="fw-bold mb-3">Calcular Frete</h5>

                <!-- Etapa 1: CEP -->
                <div class="mb-3">
                    <input
                        v-model="cep"
                        type="text"
                        placeholder="Digite seu CEP"
                        class="form-control"
                        @input="mascararCep"
                    />
                </div>

                <button class="btn btn-dark w-100" @click="buscarCep" v-if="!cepValido">
                    Buscar CEP
                </button>

                <p v-if="cepValido" class="mt-2 text-success">
                    CEP válido! Agora selecione o tipo de frete abaixo.
                </p>

                <!-- Etapa 2: Opções de frete -->
                <div v-if="cepValido" class="mt-3">
                    <label class="form-label fw-bold">Opções de Frete:</label>
                    <div class="form-check" v-for="opcao in opcoesFrete" :key="opcao.id">
                        <input
                            class="form-check-input"
                            type="radio"
                            :id="opcao.id"
                            :value="opcao.valor"
                            v-model="tipoFrete"
                            @change="selecionarFrete(opcao)"
                        />
                        <label class="form-check-label" :for="opcao.id">
                            {{ opcao.nome }} - R$ {{ opcao.valor.toFixed(2) }} (Entrega em {{ opcao.prazo }})
                        </label>
                    </div>
                </div>

                <!-- Etapa 3: Mostrar frete selecionado -->
                <p v-if="freteCalculado" class="mt-2 text-success">
                    Frete selecionado: R$ {{ frete.toFixed(2) }} – Chega em {{ prazoEntrega }}
                </p>
            </div>

            <!-- Cupom -->
            <div class="mt-4 p-3">
                <h5 class="fw-bold mb-3">Cupom de desconto</h5>
                <div class="row g-2">
                    <div class="col-md-6">
                        <input
                            v-model="cupom"
                            type="text"
                            placeholder="Digite seu cupom promocional"
                            class="form-control"
                        />
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-dark w-100" @click="aplicarCupom">
                            Aplicar
                        </button>
                    </div>
                </div>
                <p v-if="desconto > 0" class="mt-2 text-success">
                    Cupom aplicado! Você ganhou R$ {{ desconto.toFixed(2) }} de desconto.
                </p>
            </div>

            <!-- Resumo do pedido -->
            <div class="mt-4 text-end">
                <div style="max-width: 350px; margin-left: auto;">
                    <h5 class="fw-bold mb-3">Resumo do Pedido</h5>

                    <div class="d-flex justify-content-between mb-2">
                        <span>Total antes do desconto:</span>
                        <span>R$ {{ total.toFixed(2) }}</span>
                    </div>

                    <div v-if="desconto > 0" class="d-flex justify-content-between mb-2 text-success">
                        <span>Desconto:</span>
                        <span>- R$ {{ desconto.toFixed(2) }}</span>
                    </div>

                    <div class="d-flex justify-content-between mb-2">
                        <span>Frete:</span>
                        <span>R$ {{ frete.toFixed(2) }}</span>
                    </div>

                    <hr>

                    <div class="d-flex justify-content-between fw-bold fs-5">
                        <span>Total:</span>
                        <span>R$ {{ totalComDesconto.toFixed(2) }}</span>
                    </div>

                    <button class="btn btn-dark bg-gradient mt-3 w-100 py-2 fw-bold" @click="$router.push('/pagamento')">
                        Finalizar Compra
                    </button>
                </div>
            </div>
        </div>

        <!-- Carrinho vazio -->
        <div v-else class="text-center py-5">
            <h5 class="text-muted">Seu carrinho está vazio.</h5>
            <router-link to="/" class="btn btn-dark bg-gradient mt-3">
                Voltar para Loja
            </router-link>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, computed } from "vue";
import Navbar from "@/components/Navbar.vue";

// Carrinho
const carrinho = ref([
    {
        titulo: "Clean Code",
        autor: "Robert C. Martin",
        preco: 89.9,
        quantidade: 1,
        imagem: "https://m.media-amazon.com/images/I/41xShlnTZTL._SX376_BO1,204,203,200_.jpg",
    },
    {
        titulo: "O Senhor dos Anéis",
        autor: "J.R.R. Tolkien",
        preco: 120.5,
        quantidade: 2,
        imagem: "https://m.media-amazon.com/images/I/81t2CVWEsUL.jpg",
    },
]);

// Quantidade
function aumentarQuantidade(index: number) {
    carrinho.value[index].quantidade++;
}
function diminuirQuantidade(index: number) {
    if (carrinho.value[index].quantidade > 1) carrinho.value[index].quantidade--;
}
function removerItem(index: number) {
    carrinho.value.splice(index, 1);
}

// Totais
const total = computed(() => carrinho.value.reduce((s, i) => s + i.preco * i.quantidade, 0));
const desconto = ref(0);
const frete = ref(0);
const totalComDesconto = computed(() => total.value - desconto.value + frete.value);

// Cupom
const cupom = ref("");
function aplicarCupom() {
    if (cupom.value.toLowerCase() === "eco10") {
        desconto.value = total.value * 0.1;
    } else {
        desconto.value = 0;
        alert("Cupom inválido");
    }
}

// CEP e frete
const cep = ref("");
const cepValido = ref(false);
const tipoFrete = ref("");
const freteCalculado = ref(false);
const prazoEntrega = ref("");
const opcoesFrete = [
    { id: "padrao", nome: "Padrão", valor: 10, prazo: "5-7 dias úteis" },
    { id: "expresso", nome: "Expresso", valor: 20, prazo: "2-3 dias úteis" },
    { id: "rapido", nome: "Rápido", valor: 30, prazo: "1-2 dias úteis" },
];

function buscarCep() {
    const cepLimpo = cep.value.replace(/\D/g, "");
    if (cepLimpo.length !== 8) {
        alert("Por favor, insira um CEP válido com 8 números.");
        cepValido.value = false;
        return;
    }
    cepValido.value = true;
}

function selecionarFrete(opcao: { valor: number; prazo: string }) {
    frete.value = opcao.valor;
    prazoEntrega.value = opcao.prazo;
    freteCalculado.value = true;
}

function mascararCep() {
    // Remove tudo que não é número
    let valor = cep.value.replace(/\D/g, "");

    // Limita a 8 dígitos
    if (valor.length > 8) valor = valor.slice(0, 8);

    // Adiciona o hífen no formato 00000-000
    if (valor.length > 5) {
        valor = valor.slice(0, 5) + "-" + valor.slice(5);
    }

    cep.value = valor;
}
</script>

<style scoped>
.btn-link {
    text-decoration: none;
}
.btn-link:hover {
    color: #000;
}
</style>

<template>
    <!-- Navbar -->
    <Navbar />

    <div class="container my-5">
        <h2 class="text-center mb-4">Finalizar Compra</h2>

        <form @submit.prevent="proximaEtapa">
            <!-- Seleção de Endereço -->
            <div class="mb-4">
                <h5>Escolha um endereço de entrega</h5>
                <div v-for="endereco in enderecos" :key="endereco.id" class="form-check mb-2">
                    <input class="form-check-input" type="radio" :id="'endereco-' + endereco.id"
                           v-model="cliente.enderecoSelecionado" :value="endereco">
                    <label class="form-check-label" :for="'endereco-' + endereco.id">
                        {{ endereco.rua }}, {{ endereco.numero }} - {{ endereco.cidade }}/{{ endereco.estado }}
                    </label>
                </div>
            </div>

            <!-- Seleção de Forma de Pagamento -->
            <div class="mb-4">
                <h5>Escolha a forma de pagamento</h5>

                <!-- Cartões -->
                <div v-for="cartao in cartoes" :key="cartao.id" class="form-check mb-2">
                    <input class="form-check-input" type="radio" :id="'cartao-' + cartao.id"
                           v-model="cliente.pagamentoSelecionado" :value="cartao">
                    <label class="form-check-label" :for="'cartao-' + cartao.id">
                        Cartão: **** **** **** {{ cartao.numero.substr(-4) }} - {{ cartao.nome }}
                    </label>
                </div>

                <!-- Boleto -->
                <div class="form-check mb-2">
                    <input class="form-check-input" type="radio" id="boleto" v-model="cliente.pagamentoSelecionado" value="boleto">
                    <label class="form-check-label" for="boleto">Boleto Bancário</label>
                </div>

                <!-- PIX -->
                <div class="form-check mb-2">
                    <input class="form-check-input" type="radio" id="pix" v-model="cliente.pagamentoSelecionado" value="pix">
                    <label class="form-check-label" for="pix">PIX</label>
                </div>
            </div>

            <button type="submit" class="btn btn-dark bg-gradient w-100">Continuar</button>
        </form>
    </div>
</template>

<script setup lang="ts">
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import Navbar from "@/components/Navbar.vue";

const router = useRouter()

// Dados simulados do cliente logado
const enderecos = reactive([
    { id: 1, rua: 'Rua A', numero: 123, cidade: 'São Paulo', estado: 'SP' },
    { id: 2, rua: 'Rua B', numero: 456, cidade: 'São Paulo', estado: 'SP' }
])

const cartoes = reactive([
    { id: 1, numero: '1111222233334444', nome: 'Matheus Carvalho', validade: '12/25' },
    { id: 2, numero: '5555666677778888', nome: 'Matheus Carvalho', validade: '06/26' }
])

const cliente = reactive({
    enderecoSelecionado: enderecos[0],
    pagamentoSelecionado: cartoes[0]
})

const proximaEtapa = () => {
    if (!cliente.enderecoSelecionado || !cliente.pagamentoSelecionado) {
        alert('Selecione um endereço e uma forma de pagamento.')
        return
    }
    router.push('/revisao')
}
</script>
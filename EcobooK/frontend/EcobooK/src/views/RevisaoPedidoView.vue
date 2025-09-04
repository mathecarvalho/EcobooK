<template>
    <!-- Navbar -->
    <Navbar />

    <div class="container my-5">
        <h2 class="text-center mb-4">Revisar Pedido</h2>

        <div class="border p-3 rounded mb-3">
            <h5>Produtos:</h5>
            <div v-for="item in carrinho" :key="item.id" class="d-flex justify-content-between mb-2">
                <span>{{ item.quantidade }}x {{ item.titulo }}</span>
                <span>R$ {{ (item.quantidade * item.preco).toFixed(2) }}</span>
            </div>
            <div class="d-flex justify-content-between mt-2">
                <strong>Total:</strong>
                <strong>R$ {{ total.toFixed(2) }}</strong>
            </div>
        </div>

        <div class="border p-3 rounded mb-3">
            <h5>Endereço de Entrega:</h5>
            <p>{{ cliente.enderecoSelecionado.rua }}, {{ cliente.enderecoSelecionado.numero }} - {{ cliente.enderecoSelecionado.cidade }}/{{ cliente.enderecoSelecionado.estado }}</p>
        </div>

        <div class="border p-3 rounded mb-3">
            <h5>Forma de Pagamento:</h5>
            <p v-if="cliente.pagamentoSelecionado.numero">
                Cartão: **** **** **** {{ cliente.pagamentoSelecionado.numero.substr(-4) }} - {{ cliente.pagamentoSelecionado.nome }}
            </p>
            <p v-else>
                {{ cliente.pagamentoSelecionado | capitalize }}
            </p>
        </div>

        <button class="btn btn-dark bg-gradient w-100" @click="confirmarCompra">Confirmar Pedido</button>
    </div>
</template>

<script setup lang="ts">
import { reactive, computed } from 'vue'
import { useRouter } from 'vue-router'
import Navbar from "@/components/Navbar.vue";

const router = useRouter()

const carrinho = reactive([
    { id: 1, titulo: 'Livro A', preco: 50, quantidade: 1 },
    { id: 2, titulo: 'Livro B', preco: 35, quantidade: 2 },
])

// Simulação de dados selecionados no checkout
const cliente = reactive({
    enderecoSelecionado: { id: 1, rua: 'Rua A', numero: 123, cidade: 'São Paulo', estado: 'SP' },
    pagamentoSelecionado: { id: 1, numero: '1111222233334444', nome: 'Matheus Carvalho', validade: '12/25' }
})

const total = computed(() => carrinho.reduce((acc, item) => acc + item.preco * item.quantidade, 0))

const confirmarCompra = () => {
    const pedidoId = Math.floor(Math.random() * 10000)
    router.push({ name: 'confirmacao', params: { id: pedidoId } })
}
</script>

<script>
export default {
    filters: {
        capitalize(value) {
            if (!value) return ''
            return value.charAt(0).toUpperCase() + value.slice(1)
        }
    }
}
</script>
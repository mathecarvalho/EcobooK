<template>
    <!-- Navbar -->
    <Navbar />

    <div class="container my-5">
        <h2 class="text-center mb-4">Meus Pedidos</h2>

        <div v-if="pedidos.length === 0" class="text-center">
            Você ainda não realizou nenhum pedido.
        </div>

        <div class="row g-3">
            <div v-for="pedido in pedidos" :key="pedido.id" class="col-12 col-md-6 col-lg-4">
                <div class="card h-100 shadow-sm cursor-pointer" @click="selecionarPedido(pedido)">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <strong>Pedido #{{ pedido.id }}</strong>
                        <span :class="statusClass(pedido.status)" class="badge rounded-pill">{{ pedido.status }}</span>
                    </div>
                    <div class="card-body">
                        <p class="mb-1"><strong>Data:</strong> {{ pedido.data }}</p>
                        <p class="mb-1"><strong>Pagamento:</strong> {{ pedido.pagamento }}</p>
                        <p class="mb-1"><strong>Itens:</strong> {{ pedido.itens.length }}</p>
                        <p class="mb-0"><strong>Total:</strong> R$ {{ pedido.total.toFixed(2) }}</p>
                    </div>
                </div>
            </div>
        </div>

        <DetalhesPedido
            v-if="pedidoSelecionado"
            :pedido="pedidoSelecionado"
            @fechar="pedidoSelecionado = null"
        />
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import DetalhesPedido from "./PedidoDetalhesView.vue";
import Navbar from "@/components/Navbar.vue";

// Simulação de pedidos
const pedidos = ref([
    {
        id: 101,
        data: "2025-08-10",
        status: "Entregue",
        itens: [
            { nome: "Livro A", quantidade: 2, preco: 50 },
            { nome: "Livro B", quantidade: 1, preco: 30 },
        ],
        pagamento: "Cartão de Crédito",
        total: 130
    },
    {
        id: 102,
        data: "2025-08-15",
        status: "Em trânsito",
        itens: [
            { nome: "Livro C", quantidade: 1, preco: 40 }
        ],
        pagamento: "Pix",
        total: 40
    },
    {
        id: 103,
        data: "2025-08-20",
        status: "Processando",
        itens: [
            { nome: "Livro D", quantidade: 3, preco: 25 }
        ],
        pagamento: "Boleto",
        total: 75
    },
]);

const pedidoSelecionado = ref(null);

function selecionarPedido(pedido) {
    pedidoSelecionado.value = pedido;
}

// Define classes de cores por status
function statusClass(status) {
    switch (status) {
        case "Processando":
            return "bg-warning text-dark";
        case "Em trânsito":
            return "bg-info text-dark";
        case "Entregue":
            return "bg-success";
        case "Cancelado":
            return "bg-danger";
        default:
            return "bg-secondary";
    }
}
</script>

<style scoped>
.cursor-pointer {
    cursor: pointer;
}
</style>

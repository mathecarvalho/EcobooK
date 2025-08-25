<template>

    <div class="modal fade show d-block" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Pedido #{{ pedido.id }}</h5>
                    <button type="button" class="btn-close" @click="$emit('fechar')"></button>
                </div>
                <div class="modal-body">
                    <p><strong>Data do pedido:</strong> {{ pedido.data }}</p>

                    <h6>Status do pedido:</h6>
                    <ul class="timeline list-unstyled">
                        <li
                            v-for="(s, index) in statusTimeline"
                            :key="index"
                            class="d-flex align-items-center mb-3"
                        >
                            <span :class="statusCircleClass(s.status)" class="me-3"></span>
                            <div>
                                <strong>{{ s.status }}</strong>
                                <div class="text-muted">{{ s.data }}</div>
                            </div>
                        </li>
                    </ul>

                    <h6>Itens do pedido:</h6>
                    <ul class="list-group">
                        <li v-for="item in itensPedido" :key="item.id" class="list-group-item d-flex justify-content-between">
                            <span>{{ item.nome }} (x{{ item.quantidade }})</span>
                            <span>R$ {{ item.preco.toFixed(2) }}</span>
                        </li>
                    </ul>

                    <p class="mt-3 text-end"><strong>Total: R$ {{ total.toFixed(2) }}</strong></p>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" @click="$emit('fechar')">Fechar</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { computed } from "vue";
const props = defineProps({
    pedido: Object
});

// Simulação de timeline de status
const statusTimeline = [
    { status: "Processando", data: "2025-08-20" },
    { status: "Em trânsito", data: "2025-08-21" },
    { status: "Entregue", data: "2025-08-23" }
];

// Simulação de itens do pedido
const itensPedido = [
    { id: 1, nome: "Livro A", quantidade: 2, preco: 50 },
    { id: 2, nome: "Livro B", quantidade: 1, preco: 30 },
];

const total = computed(() => itensPedido.reduce((acc, item) => acc + item.preco * item.quantidade, 0));

// Define cores dos status na timeline
function statusCircleClass(status) {
    switch (status) {
        case "Processando":
            return "bg-warning";
        case "Em trânsito":
            return "bg-info";
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
.modal {
    background: rgba(0, 0, 0, 0.5);
}

/* Timeline */
.timeline li {
    position: relative;
    padding-left: 30px;
}
.timeline li::before {
    content: "";
    position: absolute;
    left: 9px;
    top: 0;
    bottom: 0;
    width: 2px;
    background: #dee2e6;
}
.timeline li:last-child::before {
    height: 12px;
}
.timeline span {
    display: inline-block;
    width: 18px;
    height: 18px;
    border-radius: 50%;
}
</style>

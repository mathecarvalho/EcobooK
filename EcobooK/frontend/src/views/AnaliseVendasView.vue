<template>
    <!-- Marca no canto superior esquerdo -->
    <div class="position-absolute top-0 start-0 p-3">
        <h2 class="fw-bold text-dark">EcobooK</h2>
    </div>

    <div class="container my-5">
        <h2 class="text-center fw-bold mb-4">Histórico de Vendas</h2>

        <!-- Filtros -->
        <div class="row mb-4">
            <div class="col-md-3 mb-2">
                <label class="form-label">Data Início</label>
                <input type="date" class="form-control" v-model="filtros.dataInicio" />
            </div>
            <div class="col-md-3 mb-2">
                <label class="form-label">Data Fim</label>
                <input type="date" class="form-control" v-model="filtros.dataFim" />
            </div>
            <div class="col-md-3 mb-2">
                <label class="form-label">Produto</label>
                <select class="form-select" v-model="filtros.produto">
                    <option value="">Todos</option>
                    <option v-for="p in produtos" :key="p.id" :value="p.nome">{{ p.nome }}</option>
                </select>
            </div>
            <div class="col-md-3 mb-2">
                <label class="form-label">Categoria</label>
                <select class="form-select" v-model="filtros.categoria">
                    <option value="">Todas</option>
                    <option v-for="c in categorias" :key="c.id" :value="c.nome">{{ c.nome }}</option>
                </select>
            </div>
        </div>

        <div class="text-center mb-4">
            <button class="btn btn-dark" @click="consultarVendas">Consultar</button>
        </div>

        <!-- Gráfico -->
        <div>
            <canvas id="graficoVendas"></canvas>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import Chart from 'chart.js/auto';

// Dados de exemplo (você pode substituir pela API)
const produtos = ref([
    { id: 1, nome: 'Livro A' },
    { id: 2, nome: 'Livro B' },
    { id: 3, nome: 'Livro C' },
]);

const categorias = ref([
    { id: 1, nome: 'Ficção' },
    { id: 2, nome: 'Não Ficção' },
    { id: 3, nome: 'Infantil' },
]);

const filtros = ref({
    dataInicio: '',
    dataFim: '',
    produto: '',
    categoria: '',
});

let chartInstance = null;

const consultarVendas = () => {
    // Aqui você faria a requisição à sua API passando os filtros
    // Para exemplo, vamos gerar dados aleatórios
    const datas = [];
    const vendas = [];
    const inicio = new Date(filtros.value.dataInicio);
    const fim = new Date(filtros.value.dataFim);

    if (!filtros.value.dataInicio || !filtros.value.dataFim) {
        alert('Selecione o período!');
        return;
    }

    let currentDate = new Date(inicio);
    while (currentDate <= fim) {
        datas.push(currentDate.toISOString().split('T')[0]);
        vendas.push(Math.floor(Math.random() * 20)); // vendas aleatórias
        currentDate.setDate(currentDate.getDate() + 1);
    }

    // Atualizar gráfico
    if (chartInstance) chartInstance.destroy();

    const ctx = document.getElementById('graficoVendas').getContext('2d');
    chartInstance = new Chart(ctx, {
        type: 'line',
        data: {
            labels: datas,
            datasets: [
                {
                    label: filtros.value.produto || filtros.value.categoria || 'Vendas',
                    data: vendas,
                    borderColor: 'rgba(54, 162, 235, 1)',
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    tension: 0.3,
                },
            ],
        },
        options: {
            responsive: true,
            plugins: {
                legend: { display: true },
                tooltip: { mode: 'index', intersect: false },
            },
            interaction: { mode: 'nearest', axis: 'x', intersect: false },
            scales: {
                x: { title: { display: true, text: 'Data' } },
                y: { title: { display: true, text: 'Quantidade de Vendas' }, beginAtZero: true },
            },
        },
    });
};
</script>

<style scoped>
/* Apenas pequenos ajustes visuais */
</style>
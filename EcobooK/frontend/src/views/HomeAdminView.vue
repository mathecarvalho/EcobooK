<template>
    <div class="admin-home-wrapper min-vh-100 bg-light">
        <div class="container py-5">
            <h2 class="text-center mb-5">Painel do Administrador</h2>

            <!-- Cards de ação -->
            <div class="row g-4 justify-content-center">
                <!-- Gerenciar Clientes -->
                <div class="col-md-4">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="bi bi-people-fill display-4 mb-3 text-primary"></i>
                            <h5 class="card-title">Clientes</h5>
                            <p class="card-text">Visualize, edite ou remova clientes cadastrados.</p>
                            <router-link to="/adm/clientes" class="btn btn-dark">Gerenciar Clientes</router-link>
                        </div>
                    </div>
                </div>

                <!-- Gerenciar Livros -->
                <div class="col-md-4">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="bi bi-book-fill display-4 mb-3 text-success"></i>
                            <h5 class="card-title">Livros</h5>
                            <p class="card-text">Adicione, edite ou remova livros disponíveis.</p>
                            <router-link to="/adm/livros" class="btn btn-dark">Gerenciar Livros</router-link>
                        </div>
                    </div>
                </div>

                <!-- Outras funcionalidades (opcional) -->
                <div class="col-md-4">
                    <div class="card shadow-sm h-100">
                        <div class="card-body text-center">
                            <i class="bi bi-bar-chart-fill display-4 mb-3 text-warning"></i>
                            <h5 class="card-title">Relatórios</h5>
                            <p class="card-text">Visualize estatísticas de vendas e clientes.</p>
                            <router-link to="/adm/relatorios" class="btn btn-dark">Ver Relatórios</router-link>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>

<script setup lang="ts">
// Nenhuma lógica especial necessária por enquanto
</script>

<style scoped>
.admin-home-wrapper {
    background-color: #f8f9fa;
}
.card {
    border-radius: 1rem;
}
.card i {
    font-size: 3rem;
}
</style>

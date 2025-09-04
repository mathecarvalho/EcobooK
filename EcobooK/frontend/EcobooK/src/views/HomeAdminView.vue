<template>
    <div class="admin-home-wrapper min-vh-100 bg-light">
        <div class="container py-5">

            <!-- Header com informações do usuário -->
            <div class="user-info-card shadow-sm mb-5 p-4 d-flex align-items-center justify-content-between">
                <div class="d-flex align-items-center">
                    <div class="avatar bg-dark text-white d-flex align-items-center justify-content-center me-3">
                        <i class="bi bi-person-fill"></i>
                    </div>
                    <div>
                        <h4 class="mb-1 fw-bold">{{ usuario.nome }}</h4>
                        <p class="mb-0 text-muted">{{ usuario.email }}</p>
                    </div>
                </div>
                <span class="badge rounded-pill bg-primary px-3 py-2">{{ usuario.papel }}</span>
            </div>

            <!-- Título -->
            <h2 class="text-center mb-5 fw-bold">Painel do Administrador</h2>

            <!-- Cards de ação -->
            <div class="row g-4 justify-content-center">
                <!-- Gerenciar Clientes -->
                <div class="col-md-4">
                    <div class="card shadow h-100 action-card">
                        <div class="card-body text-center">
                            <i class="bi bi-people-fill icon text-primary"></i>
                            <h5 class="card-title mt-2">Clientes</h5>
                            <p class="card-text">Visualize, edite ou remova clientes cadastrados.</p>
                            <router-link to="/adm/clientes" class="btn btn-dark w-100">Gerenciar Clientes</router-link>
                        </div>
                    </div>
                </div>

                <!-- Gerenciar Livros -->
                <div class="col-md-4">
                    <div class="card shadow h-100 action-card">
                        <div class="card-body text-center">
                            <i class="bi bi-book-fill icon text-success"></i>
                            <h5 class="card-title mt-2">Livros</h5>
                            <p class="card-text">Adicione, edite ou remova livros disponíveis.</p>
                            <router-link to="/adm/livros" class="btn btn-dark w-100">Gerenciar Livros</router-link>
                        </div>
                    </div>
                </div>

                <!-- Análise -->
                <div class="col-md-4">
                    <div class="card shadow h-100 action-card">
                        <div class="card-body text-center">
                            <i class="bi bi-bar-chart-fill icon text-warning"></i>
                            <h5 class="card-title mt-2">Análise</h5>
                            <p class="card-text">Visualize estatísticas de vendas e clientes.</p>
                            <router-link to="/adm/analise" class="btn btn-dark w-100">Analisar</router-link>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</template>

<script setup lang="ts">
import { ref } from "vue"

const usuario = ref({
    nome: "Matheus Carvalho",
    email: "matheus@email.com",
    papel: "Administrador"
})
</script>

<style scoped>
.admin-home-wrapper {
    background-color: #f8f9fa;
}

/* Card do usuário logado */
.user-info-card {
    background: white;
    border-radius: 1rem;
}

.avatar {
    width: 60px;
    height: 60px;
    border-radius: 50%;
    font-size: 1.8rem;
}

/* Cards de ação */
.action-card {
    border-radius: 1rem;
    transition: transform 0.2s ease, box-shadow 0.2s ease;
}
.action-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
}
.icon {
    font-size: 3rem;
}
</style>

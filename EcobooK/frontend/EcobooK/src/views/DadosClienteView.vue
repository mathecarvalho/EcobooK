<template>
    <div class="wrapper">
        <div class="container my-5">
            <div class="card shadow p-4 rounded-3">
                <h2 class="text-center mb-4">Área do Usuário</h2>

                <div class="row g-4">
                    <!-- Perfil -->
                    <div class="col-md-4">
                        <div class="card h-100 text-center shadow-sm option-card">
                            <div class="card-body d-flex flex-column justify-content-center">
                                <i class="bi bi-person-circle fs-1 mb-3"></i>
                                <h5 class="card-title">Perfil</h5>
                                <p class="card-text">Gerencie suas informações pessoais.</p>
                                <router-link to="/editar" class="btn btn-dark mt-auto">Acessar</router-link>
                            </div>
                        </div>
                    </div>

                    <!-- Meus Pedidos -->
                    <div class="col-md-4">
                        <div class="card h-100 text-center shadow-sm option-card">
                            <div class="card-body d-flex flex-column justify-content-center">
                                <i class="bi bi-bag-check fs-1 mb-3"></i>
                                <h5 class="card-title">Meus Pedidos</h5>
                                <p class="card-text">Acompanhe o status de suas compras.</p>
                                <router-link to="/pedidos" class="btn btn-dark mt-auto">Acessar</router-link>
                            </div>
                        </div>
                    </div>

                    <!-- Favoritos -->
                    <div class="col-md-4">
                        <div class="card h-100 text-center shadow-sm option-card">
                            <div class="card-body d-flex flex-column justify-content-center">
                                <i class="bi bi-heart fs-1 mb-3"></i>
                                <h5 class="card-title">Favoritos</h5>
                                <p class="card-text">Veja os livros que você salvou.</p>
                                <router-link to="/usuario/favoritos" class="btn btn-dark mt-auto">Acessar</router-link>
                            </div>
                        </div>
                    </div>

                    <!-- Configurações -->
                    <div class="col-md-4">
                        <div class="card h-100 text-center shadow-sm option-card">
                            <div class="card-body d-flex flex-column justify-content-center">
                                <i class="bi bi-gear fs-1 mb-3"></i>
                                <h5 class="card-title">Configurações</h5>
                                <p class="card-text">Ajuste suas preferências.</p>
                                <router-link to="/usuario/configuracoes" class="btn btn-dark mt-auto">Acessar</router-link>
                            </div>
                        </div>
                    </div>

                    <!-- Sair -->
                    <div class="col-md-4">
                        <div class="card h-100 text-center shadow-sm option-card">
                            <div class="card-body d-flex flex-column justify-content-center">
                                <i class="bi bi-box-arrow-right fs-1 mb-3"></i>
                                <h5 class="card-title">Sair</h5>
                                <p class="card-text">Encerre sua sessão de forma segura.</p>
                                <button @click="logout" class="btn btn-dark mt-auto">Sair</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { useRouter } from "vue-router";

const router = useRouter();

function logout() {
    // Aqui você limpa o token/localStorage e redireciona para login
    localStorage.removeItem("token");
    router.push("/login");
}
</script>

<style scoped>
.option-card {
    transition: transform 0.2s ease-in-out;
}
.option-card:hover {
    transform: scale(1.05);
}
</style>

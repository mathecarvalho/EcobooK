<template>
    <div>
        <!-- Navbar principal -->
        <nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
            <div class="container">
                <!-- Logo -->
                <router-link class="navbar-brand fw-bold text-dark fs-3" to="/">EcobooK</router-link>

                <!-- Campo de busca centralizado com ícone à direita -->
                <div class="mx-auto w-25">
                    <div class="input-group input-group-sm">
                        <input type="search" class="form-control border-dark" placeholder="Buscar livros..."/>
                        <span class="input-group-text bg-white border-dark"><i class="bi bi-search fs-5"></i></span>
                    </div>
                </div>

                <!-- Ícones à direita -->
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <router-link class="nav-link text-dark me-3" to="/carrinho">
                            <i class="bi bi-cart fs-4"></i>
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link text-dark me-3" to="/cadastrar">
                            <i class="bi bi-person fs-4"></i>
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link text-dark me-3" to="/configuracao">
                            <i class="bi bi-gear fs-4"></i>
                        </router-link>
                    </li>
                </ul>
            </div>
        </nav>

        <!-- Barra de opções abaixo -->
        <div class="bg-dark py-2">
            <div class="container d-flex justify-content-center gap-3">
                <button class="btn btn-dark text-white border-0">Categorias</button>
                <button class="btn btn-dark text-white border-0">Mais vendidos</button>
                <button class="btn btn-dark text-white border-0">Novidades</button>
                <button class="btn btn-dark text-white border-0">Promoções</button>
            </div>
        </div>
    </div>
</template>
<script setup lang="ts">
</script>
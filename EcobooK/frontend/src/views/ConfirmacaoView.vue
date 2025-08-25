<template>
    <!-- Navbar -->
    <Navbar />

    <div class="container text-center my-5">
        <h2 class="text-success mb-3">Compra Finalizada!</h2>
        <p>Seu pedido <span class="fw-bold">#{{ pedidoId }}123</span> foi realizado com sucesso.</p>
        <p>Em breve você receberá um e-mail com os detalhes.</p>
        <router-link to="/" class="btn btn-dark bg-gradient mt-3">Voltar à Loja</router-link>
    </div>
</template>
<script setup lang="ts">
import Navbar from "@/components/Navbar.vue";
</script>
<template>
    <div class="wrapper">
        <div class="container d-flex justify-content-center align-items-center min-vh-100 position-relative">

            <!-- Marca no canto superior esquerdo -->
            <div class="position-absolute top-0 start-0 p-3">
                <h2 class="fw-bold text-dark">EcobooK</h2>
            </div>

            <!-- Card de login -->
            <div class="card shadow rounded-3 p-4 m-4" style="max-width: 420px; width: 100%;">
                <h3 class="text-center mb-4">Login</h3>

                <form @submit.prevent="handleLogin">
                    <!-- E-mail -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">E-mail</label>
                        <input type="email" class="form-control" placeholder="Digite seu e-mail" v-model="form.email"/>
                    </div>

                    <!-- Senha -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Senha</label>
                        <input type="password" class="form-control" placeholder="Digite sua senha" v-model="form.senha"/>
                    </div>

                    <!-- Botões -->
                    <div class="d-grid gap-2">
                        <button class="btn btn-dark fw-bold" type="submit">Entrar como Cliente</button>
                        <button class="btn btn-outline-dark fw-bold" type="button" @click="handleAdminLogin">
                            Entrar como Administrador
                        </button>
                        <button class="btn btn-secondary fw-bold" type="button" @click="handleRegister">
                            Cadastrar-se
                        </button>
                    </div>

                    <!-- Link esqueci senha -->
                    <div class="text-center mt-3">
                        <a href="#" class="text-decoration-none text-dark">Esqueci minha senha</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { reactive } from "vue"
import { useRouter } from "vue-router"

const router = useRouter()

const form = reactive({
    email: "",
    senha: ""
})

function handleLogin() {
    console.log("Login cliente:", form)
    router.push("/") // rota exemplo
}

function handleAdminLogin() {
    console.log("Login administrador:", form)
    router.push("/adm/home") // rota exemplo
}

function handleRegister() {
    router.push("/cadastrar")
}
</script>

<style scoped>
.wrapper {
    background-color: #fff;
}

h3 {
    font-weight: 600;
}

label {
    margin-bottom: 0.5rem;
    font-weight: 600;
}

input {
    padding: 0.6rem;
    font-size: 1rem;
    border-radius: 4px;
    border: 1px solid #222;
}

button {
    padding: 0.7rem;
    border-radius: 4px;
}

button:hover {
    opacity: 0.9;
}
</style>

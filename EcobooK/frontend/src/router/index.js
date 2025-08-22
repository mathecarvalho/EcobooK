import { createRouter, createWebHistory } from 'vue-router';

import Home from "@/views/Home.vue";
import CadastrarClienteView from "@/views/CadastrarClienteView.vue";
import GerenciarClienteView from "@/views/GerenciarClienteView.vue";
import EditarClienteView from "@/views/EditarClienteView.vue";
import HomeAdminView from "@/views/HomeAdminView.vue";
import DadosClienteView from "@/views/DadosClienteView.vue";
import ProdutoDetalhes from "@/views/ProdutoDetalhes.vue";
import CadastrarLivroView from "@/views/CadastrarLivroView.vue";
import Login from "@/views/Login.vue";
import GerenciarLivrosView from "@/views/GerenciarLivrosView.vue";

const routes = [
    { path: '/', name: 'home', component: Home },
    { path: '/login', name: 'login', component: Login },
    { path: '/cadastrar', name: 'cadastrar', component: CadastrarClienteView },
    { path: '/editar', name: 'editar', component: EditarClienteView },
    { path: '/carrinho', name: 'carrinho', component: Home },
    { path: '/comprar', name: 'comprar', component: Home },
    { path: '/compras', name: 'compras', component: Home },
    { path: '/configuracao', name: 'configuracao', component: DadosClienteView },

    { path: '/buscar', name: 'resultadobusca', component: Home },
    { path: "/produto/:id", name: "ProdutoDetalhes", component: ProdutoDetalhes, props: true },

    { path: '/adm/home', name: 'admhome', component: HomeAdminView },
    { path: '/adm/clientes', name: 'clientes', component: GerenciarClienteView },
    { path: '/adm/clientes/cadastrar', name: 'adm/clientes/cadastrar', component: CadastrarClienteView },
    { path: '/adm/clientes/editar/:id', name: 'ClienteEditar', component: EditarClienteView },
    { path: '/adm/livros', name: 'adm/livros', component: GerenciarLivrosView },
    { path: '/adm/livros/cadastrar', name: 'adm/livros/cadastrar', component: CadastrarLivroView },
    { path: '/adm/livros/editar/:id', name: 'LivroEditar', component: CadastrarLivroView },
];

export const router = createRouter({
    history: createWebHistory(),
    routes
});

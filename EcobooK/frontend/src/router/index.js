import { createRouter, createWebHistory } from 'vue-router';

import Home from "@/views/Home.vue";
import CadastrarClienteView from "@/views/CadastrarClienteView.vue";
import GerenciarClienteView from "@/views/GerenciarClienteView.vue";
import EditarClienteView from "@/views/EditarClienteView.vue";
import HomeAdminView from "@/views/HomeAdminView.vue";
import DadosClienteView from "@/views/DadosClienteView.vue";
import ProdutoDetalhes from "@/views/ProdutoDetalhes.vue";
import CadastrarLivroView from "@/views/CadastrarLivroView.vue";

const routes = [
    { path: '/', name: 'home', component: Home },
    { path: '/login', name: 'login', component: Home },
    { path: '/cadastrar', name: 'cadastrar', component: CadastrarClienteView },
    { path: '/editar', name: 'editar', component: EditarClienteView },
    { path: '/carrinho', name: 'carrinho', component: Home },
    { path: '/comprar', name: 'comprar', component: Home },
    { path: '/compras', name: 'compras', component: Home },
    { path: '/configuracao', name: 'configuracao', component: DadosClienteView },

    { path: '/buscar', name: 'resultadobusca', component: Home },
    { path: "/produto/:id", name: "ProdutoDetalhes", component: ProdutoDetalhes, props: true },

    { path: '/adm', name: 'admhome', component: HomeAdminView },
    { path: '/adm/clientes', name: 'clientes', component: GerenciarClienteView },
    { path: '/adm/clientes/cadastrar', name: 'adm/clientes/cadastrar', component: CadastrarClienteView },
    { path: '/adm/clientes/editar/:id', name: 'adm/clientes/editar', component: EditarClienteView },
    { path: '/adm/livros', name: 'adm/livros', component: HomeAdminView },
    { path: '/adm/livros/cadastrar', name: 'adm/livros/cadastrar', component: CadastrarLivroView },
    { path: '/adm/livros/editar/:id', name: 'adm/livros/editar', component: HomeAdminView },
];

export const router = createRouter({
    history: createWebHistory(),
    routes
});

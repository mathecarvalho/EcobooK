import { createRouter, createWebHistory } from 'vue-router';

import HomeView from "@/views/HomeView.vue";
import CadastrarClienteView from "@/views/CadastrarClienteView.vue";
import GerenciarClienteView from "@/views/GerenciarClienteView.vue";
import EditarClienteView from "@/views/EditarClienteView.vue";
import HomeAdminView from "@/views/HomeAdminView.vue";
import DadosClienteView from "@/views/DadosClienteView.vue";
import ProdutoDetalhesView from "@/views/ProdutoDetalhesView.vue";
import CadastrarLivroView from "@/views/CadastrarLivroView.vue";
import LoginView from "@/views/LoginView.vue";
import GerenciarLivrosView from "@/views/GerenciarLivrosView.vue";
import CarrinhoView from "@/views/CarrinhoView.vue";
import PagamentoView from "@/views/PagamentoView.vue";
import RevisaoPedidoView from "@/views/RevisaoPedidoView.vue";
import ConfirmacaoView from "@/views/ConfirmacaoView.vue";
import PedidosView from "@/views/PedidosView.vue";
import PedidoDetalhesView from "@/views/PedidoDetalhesView.vue";
import AnaliseVendasView from "@/views/AnaliseVendasView.vue";
import EditarLivroView from "@/views/EditarLivroView.vue";

const routes = [
    { path: '/', name: 'home', component: HomeView },
    { path: '/login', name: 'login', component: LoginView },
    { path: '/cadastrar', name: 'cadastrar', component: CadastrarClienteView },
    { path: '/editar', name: 'editar', component: EditarClienteView },
    { path: '/carrinho', name: 'carrinho', component: CarrinhoView },
    { path: '/pagamento', name: 'pagamento', component: PagamentoView },
    { path: '/revisao', name: 'Revisao', component: RevisaoPedidoView },
    { path: '/confirmacao', name: 'confirmacao', component: ConfirmacaoView },
    { path: '/pedidos', name: 'Pedidos', component: PedidosView },
    { path: '/pedido', name: 'Pedido', component: PedidoDetalhesView },
    { path: '/configuracao', name: 'configuracao', component: DadosClienteView },

    { path: '/buscar', name: 'resultadobusca', component: HomeView },
    { path: '/produto/:id', name: 'ProdutoDetalhes', component: ProdutoDetalhesView, props: true },

    { path: '/adm/home', name: 'admhome', component: HomeAdminView },
    { path: '/adm/clientes', name: 'clientes', component: GerenciarClienteView },
    { path: '/adm/clientes/cadastrar', name: 'adm/clientes/cadastrar', component: CadastrarClienteView },
    { path: '/adm/clientes/editar/:id', name: 'ClienteEditar', component: EditarClienteView },
    { path: '/adm/livros', name: 'adm/livros', component: GerenciarLivrosView },
    { path: '/adm/livros/cadastrar', name: 'adm/livros/cadastrar', component: CadastrarLivroView },
    { path: '/adm/livros/editar/:id', name: 'LivroEditar', component: EditarLivroView },
    { path: '/adm/analise', name: 'Analise', component: AnaliseVendasView },
];

export const router = createRouter({
    history: createWebHistory(),
    routes
});

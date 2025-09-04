import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', name: 'home', component: () => import('../views/HomeView.vue') },
    { path: '/login', name: 'login', component: () => import('../views/LoginView.vue') },
    { path: '/cadastrar', name: 'cadastrar', component: () => import('../views/CadastrarClienteView.vue') },
    { path: '/editar', name: 'editar', component: () => import('../views/EditarClienteView.vue') },
    { path: '/carrinho', name: 'carrinho', component: () => import('../views/CarrinhoView.vue') },
    { path: '/pagamento', name: 'pagamento', component: () => import('../views/PagamentoView.vue') },
    { path: '/revisao', name: 'Revisao', component: () => import('../views/RevisaoPedidoView.vue') },
    { path: '/confirmacao', name: 'confirmacao', component: () => import('../views/ConfirmacaoView.vue') },
    { path: '/pedidos', name: 'Pedidos', component: () => import('../views/PedidosView.vue') },
    { path: '/pedido', name: 'Pedido', component: () => import('../views/PedidoDetalhesView.vue') },
    { path: '/configuracao', name: 'configuracao', component: () => import('../views/DadosClienteView.vue') },

    { path: '/buscar', name: 'resultadobusca', component: () => import('../views/HomeView.vue') },
    { path: '/produto/:id', name: 'ProdutoDetalhes', component: () => import('../views/ProdutoDetalhesView.vue'), props: true },

    { path: '/adm/home', name: 'admhome', component: () => import('../views/HomeAdminView.vue') },
    { path: '/adm/clientes', name: 'clientes', component: () => import('../views/GerenciarClienteView.vue') },
    { path: '/adm/clientes/cadastrar', name: 'adm/clientes/cadastrar', component: () => import('../views/CadastrarClienteView.vue') },
    { path: '/adm/clientes/editar/:id', name: 'ClienteEditar', component: () => import('../views/EditarClienteView.vue') },
    { path: '/adm/livros', name: 'adm/livros', component: () => import('../views/GerenciarLivrosView.vue') },
    { path: '/adm/livros/cadastrar', name: 'adm/livros/cadastrar', component: () => import('../views/CadastrarLivroView.vue') },
    { path: '/adm/livros/editar/:id', name: 'LivroEditar', component: () => import('../views/EditarLivroView.vue') },
    { path: '/adm/analise', name: 'Analise', component: () => import('../views/AnaliseVendasView.vue') },
  ],
})

export default router

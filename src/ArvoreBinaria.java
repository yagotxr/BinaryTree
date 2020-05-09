class ArvoreBinaria {

    private No root;

    public ArvoreBinaria() { // Inicializando a árvore
        root = null;
    }

    public void inserir(long codigo) {
        No novo = new No();
        novo.livro = new Livro(codigo);
        novo.dir = null;
        novo.esq = null;

        if (root == null) {
            root = novo;
        }
        else  {
            No atual = root;
            No anterior;
            if (buscar(codigo, false) != null){
                System.out.print("Este livro já existe no nosso banco!");
                return;

            }
            while(true) {
                anterior = atual;
                if (codigo <= atual.livro.getCodigo()) {
                    atual = atual.esq;
                    if (atual == null) {
                        anterior.esq = novo;
                        return;
                    }
                }
                else {
                    atual = atual.dir;
                    if (atual == null) {
                        anterior.dir = novo;
                        return;
                    }
                }
            }
        }
    }

    public void inserir(int codigo[]){
        for (int i = 0; i < codigo.length; i++) {
            inserir(codigo[i]);
        }
    }

    public No buscar(long codigo, boolean comparacao) {
        int c = 1;
        if (root == null) {
            if (comparacao == true){
                System.out.println(" comparações: " + c);
            }
            return null;
        }

        No atual = root;
        while (atual.livro.getCodigo() != codigo) {
            if(codigo < atual.livro.getCodigo() ) atual = atual.esq;
            else atual = atual.dir;
            if (atual == null) {
                if (comparacao == true){
                    System.out.println(" comparações: " + c);
                }
                return null;
            }
            c ++;
        }
        if (comparacao == true){
            System.out.println(" comparações: " + c);
        }
        return atual;
    }

    public boolean remover(long codigo) {
        if (root == null) {
            return false;
        }

        No atual = root;
        No pai = root;
        boolean filho_esq = true;

        while (atual.livro.getCodigo() != codigo) {
            pai = atual;
            if(codigo < atual.livro.getCodigo() ) {
                atual = atual.esq;
                filho_esq = true;
            }
            else {
                atual = atual.dir;
                filho_esq = false;
            }
            if (atual == null) {
                return false;
            }
        }

        if (atual.esq == null && atual.dir == null) {
            if (atual == root ) root = null;
            else if (filho_esq) pai.esq = null;
            else pai.dir = null;
        }
        else if (atual.dir == null) {
            if (atual == root) root = atual.esq;
            else if (filho_esq) pai.esq = atual.esq;
            else pai.dir = atual.esq;
        }
        else if (atual.esq == null) {
            if (atual == root) root = atual.dir;
            else if (filho_esq) pai.esq = atual.dir;
            else pai.dir = atual.dir;
        }
        else {
            No sucessor = sucessor_(atual);
            if (atual == root) root = sucessor;
            else if(filho_esq) pai.esq = sucessor;
            else pai.dir = sucessor;
            sucessor.esq = atual.esq;
        }

        return true;
    }

    public No sucessor_(No apaga) {
        No paidosucessor = apaga;
        No sucessor = apaga;
        No atual = apaga.dir;

        while (atual != null) {
            paidosucessor = sucessor;
            sucessor = atual;
            atual = atual.esq;
        }
        if (sucessor != apaga.dir) {
            paidosucessor.esq = sucessor.dir;
            sucessor.dir = apaga.dir;
        }
        return sucessor;
    }

    public void imprime() {
        System.out.print("Imprimindo: ");
        imprimir(root);
    }

    public void imprime_ordem(){
        System.out.print("Imprimindo em ordem: ");
        ordem(root);
    }

    public void ordem(No atual) {
        if (atual != null) {
            ordem(atual.esq);
            System.out.print(atual.livro.getCodigo() + " ");
            ordem(atual.dir);
        }
    }

    public void imprimir(No atual) {
        if (atual != null) {
            System.out.print(atual.livro.getCodigo() + " ");
            imprimir(atual.esq);
            imprimir(atual.dir);
        }
    }
}
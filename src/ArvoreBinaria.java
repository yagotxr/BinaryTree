class ArvoreBinaria {

    private No root;
    private int operations = 0;

    public ArvoreBinaria() { // Inicializando a árvore
        root = null;
    }

    public void inserir(long codigo) {
        No novo = new No();
        novo.livro = new Livro(codigo);
        novo.dir = null;
        novo.esq = null;

        if (root == null) {
            operations++;
            root = novo;
        }
        else  {
            No atual = root;
            No anterior;
            if (buscar(codigo, false) != null){
                operations++;
                System.out.print("Este livro já existe no nosso banco!");
                return;

            }
            while(true) {
                anterior = atual;
                if (codigo <= atual.livro.getCodigo()) {
                    operations++;
                    atual = atual.esq;
                    if (atual == null) {
                        operations++;
                        anterior.esq = novo;
                        return;
                    }
                }
                else {
                    operations++;
                    atual = atual.dir;
                    if (atual == null) {
                        operations++;
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
                operations++;
                System.out.println(" comparações: " + c);
            }
            return null;
        }

        No atual = root;
        while (atual.livro.getCodigo() != codigo) {
            if(codigo < atual.livro.getCodigo() ) {
                operations++;
                atual = atual.esq;
            }
            else {
                operations++;
                atual = atual.dir;
            }
            if (atual == null) {
                operations++;
                if (comparacao == true){
                    operations++;
                    System.out.println(" comparações: " + c);
                }
                return null;
            }
            c ++;
        }
        if (comparacao == true){
            operations++;
            System.out.println(" comparações: " + c);
        }
        return atual;
    }

    public boolean remover(long codigo) {
        if (root == null) {
            operations++;
            return false;
        }

        No atual = root;
        No pai = root;
        boolean filho_esq = true;

        while (atual.livro.getCodigo() != codigo) {
            pai = atual;
            if(codigo < atual.livro.getCodigo() ) {
                operations++;
                atual = atual.esq;
                filho_esq = true;
            }
            else {
                operations++;
                atual = atual.dir;
                filho_esq = false;
            }
            if (atual == null) {
                operations++;
                return false;
            }
        }

        if (atual.esq == null && atual.dir == null) {
            if (atual == root ) {
                operations++;
                root = null;
            }
            else if (filho_esq) {
                operations++;
                pai.esq = null;
            }
            else {
                operations++;
                pai.dir = null;
            }
        }
        else if (atual.dir == null) {
            operations++;
            if (atual == root) {
                operations++;
                root = atual.esq;
            }
            else if (filho_esq) {
                operations++;
                pai.esq = atual.esq;
            }
            else {
                operations++;
                pai.dir = atual.esq;
            }
        }
        else if (atual.esq == null) {
            operations++;
            if (atual == root) {
                operations++;
                root = atual.dir;
            }
            else if (filho_esq) {
                operations++;
                pai.esq = atual.dir;
            }
            else {
                operations++;
                pai.dir = atual.dir;
            }
        }
        else {
            No sucessor = sucessor_(atual);
            if (atual == root) {
                operations++;
                root = sucessor;
            }
            else if(filho_esq) {
                operations++;
                pai.esq = sucessor;
            }
            else {
                operations++;
                pai.dir = sucessor;
            }
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
            operations++;
            paidosucessor.esq = sucessor.dir;
            sucessor.dir = apaga.dir;
        }
        return sucessor;
    }

    public void imprime() {
        System.out.print("Imprimindo: ");
        imprimir(root);
    }


    public long getOperations() {
        return operations;
    }

    public void imprime_ordem(){
        System.out.print("Imprimindo em ordem: ");
        ordem(root);
    }

    public void ordem(No atual) {
        if (atual != null) {
            operations++;
            ordem(atual.esq);
            System.out.print(atual.livro.getCodigo() + " ");
            ordem(atual.dir);
        }
    }

    public void imprimir(No atual) {
        if (atual != null) {
            operations++;
            System.out.print(atual.livro.getCodigo() + " ");
            imprimir(atual.esq);
            imprimir(atual.dir);
        }
    }
}
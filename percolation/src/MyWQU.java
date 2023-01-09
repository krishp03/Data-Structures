public class MyWQU {

    private int[] id;
    private int[] sz;

    public MyWQU(int n) {
        id = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }

        if(sz[pRoot] < sz[qRoot]){
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else { 
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    public int find(int p) {
        while (id[p] != p) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    public static void main(String[] args) {
        MyWQU qu = new MyWQU(10);
        qu.union(1, 2);
        qu.union(4, 5);
        qu.union(6, 7);
        qu.union(0, 1);
        qu.union(2, 4);
        for(int val: qu.id){
        System.out.print(val+" ");
        }
    }

}
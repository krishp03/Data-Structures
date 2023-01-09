public class MyQU {

    private int[] id;

    public MyQU(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    public void union(int p, int q) {
        id[find(p)] = find(q);
    }

    public int find(int p) {
        while(id[p] != p){
            p = id[p];
        }
        return p;
    }
    public static void main(String[] args) {
        MyQU qu = new MyQU(10);
        qu.union(1, 2);
        qu.union(4, 5);
        qu.union(6, 7);
        qu.union(0, 1);
        qu.union(2, 4);
        for(int val: qu.id){
        System.out.print(val+" ");
        }
        System.out.println();
    }

}
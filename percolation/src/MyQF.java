public class MyQF {
    int[] id;

    public MyQF(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++)
            id[i] = i;
    }

    public void union(int p, int q) {
        if(id[p] == id[q]) {
            return;
        }
        int oldId = id[p];
        int n = id.length;
        for (int i = 0; i < n; i++){
            if(id[i] == oldId){
                id[i] = id[q];
            }
        }
    }

    public int find(int p) {
        return id[p];
    }

    public static void main(String[] args) {
        MyQF qf = new MyQF(10);
        qf.union(0, 9);
        qf.union(1, 8);
        qf.union(2, 7);
        qf.union(9, 1);
        for(int val: qf.id){
        System.out.print(val+" ");
        }
        System.out.println();
    }
}

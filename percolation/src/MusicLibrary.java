public class MusicLibrary {
    
    private Node first;
    int size;

    public class Song{
        public Song(String singer, String name, String file){
            this.singer = singer;
            this.name = name;
            this.file = file;
        }
        String singer;
        String name;
        String file;
    }
    private class Node{
        Song item;
        Node prev;
        Node next;
    }

    public void addSong(Song s){
        Node songNode = new Node();
        songNode.item = s;
    
        // Takes care of the case where this is the first song added
        if(first == null){
            size = 1;
            first = songNode;
        } 
        
        else {
            // Finds the first node which has a song with the same artist
            Node sameArtist;
            Node last = null;
            for(sameArtist = first; sameArtist != null; sameArtist = sameArtist.next){
                if(sameArtist.item.singer.equals(s.singer))
                    break;
                last = sameArtist;
            }
    
            // If there is a node which contains a song with the same artist
            if(sameArtist != null){
                // If the new song needs to be inserted as the first node
                if(sameArtist == first){
                    songNode.next = sameArtist;
                    sameArtist.prev = songNode;
                    first = songNode;
                } 
                else {
                    songNode.next = sameArtist; 
                    songNode.prev = sameArtist.prev;
                    last.next = songNode; 
                    sameArtist.prev = songNode;
                }
            } 
    
            // If the song has a new artist
            else {
                songNode.prev = last;
                last.next = songNode;
            }
    
            // increments size
            size++;
        }
    }

    private void printNode(Node n){
        System.out.print("("+n.item.singer+", "+n.item.name+")");
    }

    public void printList(){
        Node curr = first;
        Node last = null;
        while (curr != null) {
            printNode(curr);
            System.out.print("->");
            last = curr;
            curr = curr.next;
        }
        System.out.println();
        curr = last;
        while(curr != null){
            printNode(curr);
            System.out.print("->");
            curr = curr.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MusicLibrary ml = new MusicLibrary();
        Song a1 = ml.new Song("A", "A4", "");
        Song a2 = ml.new Song("A", "A3", "");
        Song a3 = ml.new Song("A", "A2", "");
        Song b1 = ml.new Song("B", "B3", "");
        Song b2 = ml.new Song("B", "B2", "");
        Song b3 = ml.new Song("B", "B1", "");
        Song a4 = ml.new Song("A", "A1", "");
         
        ml.addSong(a1);
        ml.addSong(a2);
        ml.addSong(a3);
        ml.addSong(b1);
        ml.addSong(b2);
        ml.addSong(b3);
        ml.addSong(a4);
        ml.printList();
    }
}

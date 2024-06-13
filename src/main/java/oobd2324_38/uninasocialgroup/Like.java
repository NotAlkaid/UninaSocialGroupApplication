package oobd2324_38.uninasocialgroup;

public class Like {

    public static void removeLike(Post post, Utente utente) {
        LikeDao likeDao = new LikeDao();
        likeDao.removeLike(post, utente);
    }

    public static void putLike(Post post, Utente utente) {
        LikeDao likeDao = new LikeDao();
        likeDao.putLike(post, utente);
    }
}

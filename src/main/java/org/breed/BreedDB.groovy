package org.breed

class BreedDB {
    Integer id
    String breed
    String image
    String link
    String article

    BreedDB(Integer id, String breed, String image, String link, String article) {
        this.id = id
        this.breed = breed
        this.image = image
        this.link = link
        this.article = article
    }


    @Override
     String toString() {
        return "BreedDB{" +
                "id=" + id +
                ", breed='" + breed + '\'' +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", article='" + article + '\'' +
                '}';
    }

}

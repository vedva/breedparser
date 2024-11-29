package org.breed

class BreedDB {
    Integer id
    String name
    String image
    String link
    String article

    BreedDB(Integer id, String name, String image, String link, String article) {
        this.id = id
        this.name = name
        this.image = image
        this.link = link
        this.article = article
    }

    @Override
     String toString() {
        return "BreedDB{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                ", article='" + article + '\'' +
                '}';
    }
}

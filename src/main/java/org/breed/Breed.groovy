package org.breed

class Breed {
    Integer id
    String breed
    String image
    String link
    String article

    Breed(Integer id, String breed, String image, String link, String article) {
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

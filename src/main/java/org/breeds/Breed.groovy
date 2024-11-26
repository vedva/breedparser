package org.breeds

class Breed {
    String name
    String image
    String link
    String article


    @Override
    public String toString() {
        return "{name: $this.name, image: $this.image, link: $this.link, article: $this.article}"
    }
}

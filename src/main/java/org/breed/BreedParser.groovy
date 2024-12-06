package org.breed

class BreedParser {
    String name
    String image
    String link
    String article


    @Override
    String toString() {
        return "{name: $this.name, image: $this.image, link: $this.link, article: $this.article}"
    }

    String stringToJson() {
        return "{\"breed\": \"$this.name\", \"image\": \"$this.image\", \"link\": \"$this.link\", \"article\": \"$this.article\"}"
    }
}

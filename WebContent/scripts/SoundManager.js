/**Se encarga de administrar los efectos de sonido. */
function SoundManager(){
    /**Ejecuta el efecto de sonido de una carta. */
    this.playCard = function(){
        var random = new RandomGenerator();
        var soundName = `../soundFX/card${random.randomInt(1,4)}.mp3`
        var audio = new Audio();
        audio.src = soundName;
        audio.play();
        console.log(soundName);
    }
}
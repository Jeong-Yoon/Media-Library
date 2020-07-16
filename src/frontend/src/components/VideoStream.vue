<template>
<<<<<<< HEAD
  <div class="vodeo-container"></div>
=======
  <div class="vodeo-container">
    <video
      id="player"
      controls
      crossorigin
      playsinline
      poster="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-HD.jpg"
    >
      <!-- Video files -->
      <source
        src="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-576p.mp4"
        type="video/mp4"
        size="576"
      />
      <source
        src="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-720p.mp4"
        type="video/mp4"
        size="720"
      />
      <source
        src="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-1080p.mp4"
        type="video/mp4"
        size="1080"
      />
      <source
        src="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-1440p.mp4"
        type="video/mp4"
        size="1440"
      />

      <!-- Caption files -->
      <track
        kind="captions"
        label="English"
        srclang="en"
        src="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-HD.en.vtt"
      />
      <!-- <track kind="captions" label="Français" srclang="fr" src="https://cdn.plyr.io/static/demo/View_From_A_Blue_Moon_Trailer-HD.fr.vtt"> -->
    </video>
  </div>
>>>>>>> 4f8d572aed4cc3ec2aa2ba50c82abda41bec75e9
</template>

<script>
export default {};
</script>

<style scoped>
/* This is purely for the demo */
.video-container {
  max-width: 800px;
  margin: 0 auto;
}
.plyr {
  border-radius: 4px;
  margin-bottom: 15px;
}
</style>

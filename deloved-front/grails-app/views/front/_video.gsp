
<img id="video_d"  class="icovideo1" src="${resource(dir: 'images', file: 'front/play_btn.png')}" style="width: 20%;position: absolute;top: 30%;right: 40%;cursor: pointer">


<div class="modal fade" id="video_deloved" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog movie_delo" style="width:60%;margin: 7% auto;">
        <div class="modal-content">

            <video   id="movie_d" style="width: 100%;border: 5px solid #fafafa;border-radius: 10px;" autoplay controls    >
            <source src="${resource(dir: 'video', file: 'video_deloved.mp4')}" type="video/mp4">
            </video>
        </div>



    </div>

</div>
<script>
    $(document).ready(function(){
        var arVideos =document.getElementById("movie_d");
        arVideos.pause();
        $('#video_deloved').on('shown.bs.modal', function() {
            if(arVideos.currentTime!=0) {
                arVideos.currentTime = 0;
                arVideos.play()
            }else{

                arVideos.play()
            }



        });
        $('#video_deloved').on('hidden.bs.modal', function() {
            var arVideos =document.getElementById("movie_d");
            arVideos.pause();
        });
        $("#video_d").click(function(){


            $("#video_deloved").modal("show");
            $(".modal-backdrop").css('z-index','100');

        })
    })


</script>
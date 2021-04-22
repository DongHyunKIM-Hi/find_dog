# find_dog
===========================

참여인원: 백앤드 3명 (강미진, 김동현, 임다희) / 프론트 2명 (김형민, 심현인)

</br>

강아지가 이뻐서 쉽게 분양을 받고 성향이 맞지않아 쉽게 파양을 하는 사람들이 많이 있습니다
처음부터 분양을 받기위한 까다로운 조건을 만들면 좋겠지면 개인의 힘으로는 강제하기가 쉽지 않습니다
조금이라도 파양되는 강아지의 수를 줄이고 한번 파양되서 새로운 주인을 기다리는 귀여운 강아지를 
좋은 사람에게 소개시켜주는 사이트를 만들고자 하였습니다.

개발 언어
---------
- Backend: Java 8
- Frontend: React


## 기능 (각자 개발한 부분을 적어주세요)

1. mbti 검사를 통한 자신과 성향이 맞는 강아지 추천 url: https://github.com/DongHyunKIM-Hi/mbti
2. chat봇을 통한 상담
3. 자랑하게 페이지에 게시글 조회/작성/수정/삭제를 위한 CRUD 구현
4. 자랑하게 페이지에 댓글 조회/작성/수정/삭제를 위한 CRUD 구현
5. 원하는 게시글에 좋아요를 누를 수 있는 기능 구현

</br>

## API 설계 (excel로 작성해서 보내주세요)
</br>

https://user-images.githubusercontent.com/57423518/115660770-76881e80-a377-11eb-8407-7a68a05eeb0a.png


![img.png](img.png)

</br>

## 기능소개
여기부터는 열심히 만든 기능들을 소개하면 될 것 같습니다.

##Article Entity
<pre>
<code>
    @Column
    @ElementCollection
    private List<String> likeId = new ArrayList<>();

    public Article(ArticleRequestDto requestDto){
        this.nickname = requestDto.getNickname();
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.likeCnt = requestDto.getLikeCnt();
        this.likeId = requestDto.getLikeId();

    }

    public void update(ArticleRequestDto requestDto){
        this.content = requestDto.getContent();
        this.imgUrl = requestDto.getImgUrl();
        this.likeCnt = requestDto.getLikeCnt();
        this.likeId = requestDto.getLikeId();
    }
</code>
</pre>
###
+ 자랑하개에 글을 올릴 때 필요한 항목을 전부 멤버변수로 생성하였습니다. 
+ 좋아요 기능을 게시글에 포함하여 생성하였습니다.
###

##Article Repository
<pre>
<code>
    @Transactional
    public List<String> update(Long id, ArticleRequestDto requestDto){
        Article article = articleRepository.findById(id).orElseThrow(
                () ->new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        String likeNickname = requestDto.getNickname();
        requestDto.getLikeId().add(likeNickname);
        article.update(requestDto);
        return requestDto.getLikeId();
    }
</code>
</pre>
+ 자랑하개에 있는 게시글을 수정할 때 변경 사항을 수정하여 업데이트하고 좋아요를 누른 유저들의 닉네임 리스트를 반환하여 줍니다.
+ Spring Data JPA를 사용하기 위해 JpaRepository를 상속하고 Article Entity와 연결합니다.


##Article Controller
<pre>
<code>
    @ResponseBody
    @GetMapping( "/api/article")
    public List<Article> getArticle(){

        return articleRepository.findAllByOrderByModifiedAt();
    }

    @ResponseBody
    @PostMapping("/api/article")
    public Article creatArticle(@RequestBody ArticleRequestDto requestDto){
        Article article = new Article(requestDto);
        return articleRepository.save(article);
    }

    @PutMapping( "/api/article/{article_id}")
    public  List<String>  updateArticle(@PathVariable Long article_id, @RequestBody ArticleRequestDto requestDto){
        return articleService.update(article_id, requestDto);
    }

    @DeleteMapping( "/api/article/{article_id}")
    public Long deleteArticle(@PathVariable Long article_id) {
        articleRepository.deleteById(article_id);
        return article_id;
    }
</code>
</pre>

+ 업데이트는 business layer에서 로직을 처리하고 get과 delete는 데이터를 repository에서 받아와 처리하는 방법으로 기능을 구현했습니다. 

## Comment Entity
<pre>
<code>
@Getter
@Setter
@Entity
@NoArgsConstructor
public class Comment {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column
    private String comment;

    @Column
    private String nickname;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Article article;


    public Comment(CommentRequestDto requestDto, Article article){
        this.nickname = requestDto.getNickname();
        this.comment = requestDto.getComment();
        this.article = article;
    }

    public void update(CommentRequestDto requestDto){
        this.nickname = requestDto.getNickname();
        this.comment = requestDto.getComment();
    }

</code>
</pre>

+ 댓글을 쓸때 필요한 항목을 전부 멤버변수로 생성하였습니다.

## comment Service
<pre>
<code>
 @Transactional
    public Long update(Long id, CommentRequestDto requestDto){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()->new NullPointerException("해당 게시글이 존재하지 않습니다.")
        );
        comment.update(requestDto);
        return comment.getId();
    }

</code></pre>
+ 댓글을 수정 할 때 변경내용을 dto로 받아 내용을 업데이트하고 댓글의 아이디를 반환하여 줍니다.
+ Spring Data JPA를 사용하기 위해 JpaRepository를 상속하고 Comment Entity와 연결합니다

##Comment Controller
<pre>
<code>
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentController {

    private final CommentService commentService;
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @GetMapping("/api/comment/{article_id}")
    public List<Comment> getComment(@PathVariable Long article_id){
        return commentRepository.findAllByArticleId(article_id);
    }

    @PostMapping("/api/comment/{article_id}")
    public Comment creatComment(@PathVariable Long article_id, @RequestBody CommentRequestDto requestDto){
        Article article = articleRepository.findById(article_id).get();
        Comment comment = new Comment(requestDto, article);
        return commentRepository.save(comment);

    }

    @PutMapping("/api/comment/{comment_id}")
    public Long updateComment(@PathVariable Long comment_id, @RequestBody CommentRequestDto requestDto){
        return commentService.update(comment_id, requestDto);
    }

    @DeleteMapping("/api/comment/{comment_id}")
    public Long deleteComment(@PathVariable Long comment_id){
        commentRepository.deleteById(comment_id);
        return comment_id;
    }
</code></pre>

+ 업데이트는 business layer에서 로직을 처리하고 나머지는 Repository에서 바로 받아와 처리하는 방식으로 구현하였습니다.
## 동작화면
</br>
배폴한 프론트 사진과 유튜브 동영상 링크 


package blog.it;

import de.burandt.artists.Application;
import de.burandt.artists.blog.domain.BlogPost;
import de.burandt.artists.blog.repository.BlogPostRepository;
import de.burandt.artists.blog.service.BlogPostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class BlogPostServiceIntegrationTest {

    @Autowired
    private BlogPostService blogPostService;
    @Autowired
    private BlogPostRepository blogPostRepository;

    @Test
    public void should_delete_blog_post_if_marked() {
        BlogPost blogPost = new BlogPost(new Date(), "Titel", "Beispieltext", "www.blog.com");
        BlogPost blogPost2 = new BlogPost(new Date(), "Titel2", "Beispieltext2", "www.blog.com");
        blogPost = blogPostRepository.save(blogPost);
        blogPost2 = blogPostRepository.save(blogPost2);

        blogPost.setMarkedAsDeleted(true);
        List<BlogPost> posts = new ArrayList<>();
        posts.add(blogPost);
        posts.add(blogPost2);
        blogPostService.updateBlogPosts(posts);

        List<BlogPost> result = blogPostRepository.findAll();
        assertThat(result).hasSize(1);

        blogPostRepository.deleteAll();
    }
}

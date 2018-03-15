package blog.it;

import de.burandt.artists.Application;
import de.burandt.artists.blog.domain.BlogPost;
import de.burandt.artists.blog.repository.BlogPostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class BlogRepositoryIntegrationTest {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Test
    public void should_save_and_retrieve() {
        BlogPost blogPost = new BlogPost(new Date(), "Titel", "Beispieltext", "www.blog.com");
        BlogPost blogPost2 = new BlogPost(new Date(), "Titel2", "Beispieltext2", "www.blog.com");
        blogPostRepository.save(blogPost);
        blogPostRepository.save(blogPost2);

        List<BlogPost> blogPosts = blogPostRepository.findAllByOrderByIdDesc();
        assert blogPosts.get(0).getHeadline().equals("Titel2");
        assert blogPosts.get(1).getHeadline().equals("Titel");

        blogPostRepository.deleteAll();
    }
}

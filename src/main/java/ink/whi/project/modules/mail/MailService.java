package ink.whi.project.modules.mail;

/**
 * @author: qing
 * @Date: 2023/12/16
 */
public interface MailService {

    String generateMailContent(String code, String email);
}

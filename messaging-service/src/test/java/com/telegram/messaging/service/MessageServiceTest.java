package com.telegram.messaging.service;

import com.telegram.messaging.configuration.JpaTestConfiguration;
import com.telegram.messaging.configuration.ServiceTestConfiguration;
import com.telegram.messaging.entity.Message;
import com.telegram.messaging.service.watcher.MessageWatcherService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ServiceTestConfiguration.class,
        JpaTestConfiguration.class
})
@Sql(scripts = {
        "/db/init_tables.sql",
        "/db/init_accounts.sql",
        "/db/init_dialogues.sql",
        "/db/init_messages.sql"
})
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
public class MessageServiceTest extends BaseServiceTest {

    @Autowired
    MessageService messageService;

    @Autowired
    MessageWatcherService messageWatcherService;

    @Test
    @Sql(scripts = "/db/drop_tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindAllByAccountIdAndDialogueId() {
        List<Message> messages = messageService.findAllByDialogueIdAndAccountId(GROUP_DIALOGUE_ID, SECOND_USER_ACCOUNT_ID);
        List<UUID> messageIdentifiers = messages.stream()
                .map(Message::getId)
                .toList();

        Assertions.assertThat(messageIdentifiers).contains(
                FIRST_MESSAGE_ID,
                SECOND_MESSAGE_ID,
                THIRD_MESSAGE_ID
        );
    }

    @Test
    @Sql(scripts = "/db/drop_tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testSendMessage() {
        String messageText = "This is a new message";
        UUID parentMessageId = UUID.fromString("9fcea1d3-03ac-4c68-86e4-46b48777256a");

        Message message = messageService.sendMessage(
                messageText,
                FIRST_ACCOUNT_ID,
                GROUP_DIALOGUE_ID,
                parentMessageId
        );

        assertAccountHasMessage(SECOND_USER_ACCOUNT_ID, message);
        assertAccountHasMessage(THIRD_USER_ACCOUNT_ID, message);
    }

    private void assertAccountHasMessage(UUID accountId, Message message) {
        UUID newMessageId = message.getId();
        Message newMessageInList = messageService
                .findAllByDialogueIdAndAccountId(GROUP_DIALOGUE_ID, accountId)
                .stream()
                .filter(nextMessage -> nextMessage.getId().equals(newMessageId))
                .findFirst()
                .orElse(null);

        Assertions.assertThat(newMessageInList).isNotNull();
    }


}

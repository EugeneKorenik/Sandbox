package com.telegram.messaging.service;

import com.telegram.messaging.configuration.JpaTestConfiguration;
import com.telegram.messaging.configuration.ServiceTestConfiguration;
import com.telegram.messaging.entity.Dialogue;
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
        "/db/init_dialogues.sql"
})
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
public class DialogueServiceTest extends BaseServiceTest {

    @Autowired
    DialogueService dialogueService;

    @Test
    @Sql(scripts = "/db/drop_tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindAllByUserId() {
        List<Dialogue> dialogues = dialogueService.findAllByAccountId(FIRST_ACCOUNT_ID);
        List<UUID> dialogueIdentifiers = dialogues.stream()
                .map(Dialogue::getId)
                .toList();

        Assertions.assertThat(dialogueIdentifiers).contains(
                FIRST_DIALOGUE_ID,
                GROUP_DIALOGUE_ID
        );
    }

    @Test
    @Sql(scripts = "/db/drop_tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testCreateNewDialogue() {
        String dialogueName = "newDialogueName";
        List<UUID> dialogueWatchers = List.of(THIRD_USER_ACCOUNT_ID, FIFTH_USER_ACCOUNT_ID);
        Dialogue dialogue = dialogueService.create(dialogueName, dialogueWatchers);

        assertAccountHasDialogue(THIRD_USER_ACCOUNT_ID, dialogue);
        assertAccountHasDialogue(FIFTH_USER_ACCOUNT_ID, dialogue);
    }

    private void assertAccountHasDialogue(UUID accountId, Dialogue dialogue) {
        UUID newMessageId = dialogue.getId();
        Dialogue newMessageInList = dialogueService
                .findAllByAccountId(accountId)
                .stream()
                .filter(nextDialogue -> nextDialogue.getId().equals(newMessageId))
                .findFirst()
                .orElse(null);

        Assertions.assertThat(newMessageInList).isNotNull();
    }


}

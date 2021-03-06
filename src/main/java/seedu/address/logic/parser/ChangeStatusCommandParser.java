package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEW_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORIGINAL_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ChangeStatusCommand;
import seedu.address.logic.commands.ChangeStatusCommand.ChangeStatusDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ChangeStatusCommand object
 */
public class ChangeStatusCommandParser implements Parser<ChangeStatusCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ChangeStatusCommand
     * and returns an ChangeStatusCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ChangeStatusCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUANTITY, PREFIX_ORIGINAL_STATUS, PREFIX_NEW_STATUS);
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ChangeStatusCommand.MESSAGE_USAGE), pe);
        }


        ChangeStatusDescriptor changeStatusDescriptor = new ChangeStatusDescriptor();
        changeStatusDescriptor.setQuantity(ParserUtil
                .parseQuantity(argMultimap.getValue(PREFIX_QUANTITY).get()).toInteger());
        changeStatusDescriptor.setInitialStatus(ParserUtil
                .parseStatus(argMultimap.getValue(PREFIX_ORIGINAL_STATUS).get()));
        changeStatusDescriptor.setUpdatedStatus(ParserUtil
                .parseStatus(argMultimap.getValue(PREFIX_NEW_STATUS).get()));

        return new ChangeStatusCommand(index, changeStatusDescriptor);

    }
}

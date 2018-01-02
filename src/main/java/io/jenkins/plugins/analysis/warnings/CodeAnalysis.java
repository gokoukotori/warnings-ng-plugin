package io.jenkins.plugins.analysis.warnings;

import org.kohsuke.stapler.DataBoundConstructor;

import edu.hm.hafner.analysis.AbstractParser;
import edu.hm.hafner.analysis.parser.CodeAnalysisParser;
import io.jenkins.plugins.analysis.core.steps.DefaultLabelProvider;
import io.jenkins.plugins.analysis.core.steps.StreamBasedParser;

import hudson.Extension;

/**
 * Provides a parser and customized messages for the CodeAnalysis compiler.
 *
 * @author Ullrich Hafner
 */
public class CodeAnalysis extends StreamBasedParser {
    private static final String PARSER_NAME = Messages.Warnings_CodeAnalysis_ParserName();

    @DataBoundConstructor
    public CodeAnalysis() {
        // empty constructor required for stapler
    }

    @Override
    protected AbstractParser createParser() {
        return new CodeAnalysisParser();
    }

    /**
     * Registers this tool as extension point implementation.
     */
    @Extension
    public static class Descriptor extends StaticAnalysisToolDescriptor {
        public Descriptor() {
            super(new CodeAnalysis.LabelProvider());
        }
    }

    /**
     * Provides the labels for the parser.
     */
    private static class LabelProvider extends DefaultLabelProvider {
        private LabelProvider() {
            super("code-analysis", PARSER_NAME);
        }
    }
}

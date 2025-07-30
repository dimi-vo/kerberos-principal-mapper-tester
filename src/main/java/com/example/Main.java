package com.example;

import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception {
        KerberosShortNamer kerberosShortNamer = KerberosShortNamer.fromUnparsedRules(
                "example2.com",
                List.of(
                        "RULE:[1:$1@$0](.*@example2.com)s/@.*//"
                )
        );

        System.out.println(kerberosShortNamer.shortName(KerberosName.parse("user@example2.com")));
        System.out.println(kerberosShortNamer.shortName(KerberosName.parse("user123@example2.com")));
        System.out.println(kerberosShortNamer.shortName(KerberosName.parse("user123$@example2.com")));
    }
}

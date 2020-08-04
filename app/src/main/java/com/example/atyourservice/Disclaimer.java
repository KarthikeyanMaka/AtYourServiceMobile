package com.example.atyourservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import appcommon.ErrorHandling;

public class Disclaimer extends AppCompatActivity {
    TextView distext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);

        try {
            distext = (TextView) findViewById(R.id.distext);

            String disclaimer = "Disclaimer \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "This disclaimer (\"Disclaimer\", \"Agreement\") is an agreement between Mobile Application Developer (\"Mobile Application Developer\", \"us\", \"we\" or \"our\") and you (\"User\", \"you\" or \"your\"). This Disclaimer sets forth the general guidelines, terms and conditions of your use of the Smart Serve mobile application and any of its products or services (collectively, \"Mobile Application\" or \"Services\"). \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Representation \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Any views or opinions represented in the Mobile Application are personal and belong solely to Mobile Application Developer and do not represent those of people, institutions or organizations that the owner may or may not be associated with in professional or personal capacity unless explicitly stated. Any views or opinions are not intended to malign any religion, ethnic group, club, organization, company, or individual. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Content and postings \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "You may not modify, print or copy any part of the Mobile Application. Inclusion of any part of this Mobile Application in another work, whether in printed or electronic or another form or inclusion of any part of the Mobile Application in another mobile application by embedding, framing or otherwise without the express permission of Mobile Application Developer is prohibited. Compensation and sponsorship \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Some of the links in the Mobile Application may be \"affiliate links\". This means if you click on the link and purchase an item, Mobile Application Developer will receive an affiliate commission.Reviews and testimonials \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Testimonials are received in various forms through a variety of submission methods. The testimonials are not necessarily representative of all of those who will use our products or services. Mobile Application Developer is not responsible for the opinions or comments posted in the Mobile Application, and does not necessarily share them. All opinions expressed are strictly the views of the poster or reviewer. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Some testimonials may have been edited for clarity, or shortened in cases where the original testimonial included extraneous information of no relevance to the general public. Testimonials may be reviewed for authenticity before they are posted for public viewing.Indemnification and warranties \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "While we have made every attempt to ensure that the information contained in the Mobile Application is correct. Mobile Application Developer is not responsible for any errors or omissions, or for the results obtained from the use of this information. All information and Services in the Mobile Application are provided \"as is\", with no guarantee of completeness, accuracy, timeliness or of the results obtained from the use of this information or Services, and without warranty of any kind, express or implied. In no event will Mobile Application Developer be liable to you or anyone else for any decision made or action taken in reliance on the information in the Mobile Application or Services, or for any consequential, special or similar damages, even if advised of the possibility of such damages. Information in the Mobile Application is for general information purposes only and is not intended to provide any type of professional advice. Please seek professional assistance should you require it. Furthermore, information contained in the Mobile Application and any pages linked to and from it are subject to change at any time and without warning. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "We reserve the right to modify this Disclaimer relating to the Mobile Application, products or services at any time, effective upon posting of an updated version of this Disclaimer in the Mobile Application. When we do we will post a notification in our Mobile Application. Continued use of the Mobile Application after any such changes shall constitute your consent to such changes. Policy was created with https://www.WebsitePolicies.com \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Acceptance of this disclaimer \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "You acknowledge that you have read this Disclaimer and agree to all its terms and conditions. By accessing the Mobile Application you agree to be bound by this Disclaimer. If you do not agree to abide by the terms of this Disclaimer, you are not authorized to use or access the Mobile Application. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Contacting us \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "If you would like to contact us to understand more about this Disclaimer or wish to contact us concerning any matter relating to it, you may send an email to customercare@smartserve.com \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "This document was last updated on July 7, 2020 ";
            String privacy = "Privacy policy \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "This privacy policy (\"Policy\") describes how Mobile Application Developer (\"Mobile Application Developer\", \"we\", \"us\" or \"our\") collects, protects and uses the personally identifiable information (\"Personal Information\") you (\"User\", \"you\" or \"your\") may provide in the Smart Serve mobile application and any of its products or services (collectively, \"Mobile Application\" or \"Services\"). \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "It also describes the choices available to you regarding our use of your Personal Information and how you can access and update this information. This Policy does not apply to the practices of companies that we do not own or control, or to individuals that we do not employ or manage. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Automatic collection of information \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "When you open the Mobile Application our servers automatically record information that your device sends. This data may include information such as your device's IP address and location, device name and version, operating system type and version, language preferences, information you search for in our Mobile Application, access times and dates, and other statistics. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Information collected automatically is used only to identify potential cases of abuse and establish statistical information regarding Mobile Application traffic and usage. This statistical information is not otherwise aggregated in such a way that would identify any particular user of the system. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Collection of personal information \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "You can use the Mobile Application without telling us who you are or revealing any information by which someone could identify you as a specific, identifiable individual. If, however, you wish to use some of the Mobile Application's features, you may be asked to provide certain Personal Information (for example, your name and e-mail address). We receive and store any information you knowingly provide to us when you fill any online forms in the Mobile Application.  When required, this information may include the following: \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "- Geolocation data of the mobile device such as latitude and longitude. \n" +
                    "\n" +
                    "- Certain features on the mobile device such as contacts, calendar, gallery, etc. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "You can choose not to provide us with your Personal Information, but then you may not be able to take advantage of some of the Mobile Application's features. Users who are uncertain about what information is mandatory are welcome to contact us. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Use and processing of collected information \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "In order to make our Mobile Application and Services available to you, or to meet a legal obligation, we need to collect and use certain Personal Information. If you do not provide the information that we request, we may not be able to provide you with the requested products or services. Some of the information we collect is directly from you via our Mobile Application. However, we may also collect Personal Information about you from other sources. Any of the information we collect from you may be used for the following purposes: \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "- Request user feedback \n" +
                    "\n" +
                    "- Improve user experience \n" +
                    "\n" +
                    "- Run and operate our Mobile Application and Services \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Processing your Personal Information depends on how you interact with our Mobile Application, where you are located in the world and if one of the following applies: (i) you have given your consent for one or more specific purposes; this, however, does not apply, whenever the processing of Personal Information is subject to European data protection law; (ii) provision of information is necessary for the performance of an agreement with you and/or for any pre-contractual obligations thereof; (iii) processing is necessary for compliance with a legal obligation to which you are subject; (iv) processing is related to a task that is carried out in the public interest or in the exercise of official authority vested in us; (v) processing is necessary for the purposes of the legitimate interests pursued by us or by a third party. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    " Note that under some legislations we may be allowed to process information until you object to such processing (by opting out), without having to rely on consent or any other of the following legal bases below. In any case, we will be happy to clarify the specific legal basis that applies to the processing, and in particular whether the provision of Personal Information is a statutory or contractual requirement, or a requirement necessary to enter into a contract. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Disclosure of information \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    " Depending on the requested Services or as necessary to complete any transaction or provide any service you have requested, we may share your information with your consent with our trusted third parties that work with us, any other affiliates and subsidiaries we rely upon to assist in the operation of the Mobile Application and Services available to you. We do not share Personal Information with unaffiliated third parties. These service providers are not authorized to use or disclose your information except as necessary to perform services on our behalf or comply with legal requirements. We may share your Personal Information for these purposes only with third parties whose privacy policies are consistent with ours or who agree to abide by our policies with respect to Personal Information. These third parties are given Personal Information they need only in order to perform their designated functions, and we do not authorize them to use or disclose Personal Information for their own marketing or other purposes. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Retention of information \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "We will retain and use your Personal Information for the period necessary to comply with our legal obligations, resolve disputes, and enforce our agreements unless a longer retention period is required or permitted by law. We may use any aggregated data derived from or incorporating your Personal Information after you update or delete it, but not in a manner that would identify you personally. Once the retention period expires, Personal Information shall be deleted. Therefore, the right to access, the right to erasure, the right to rectification and the right to data portability cannot be enforced after the expiration of the retention period. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "The rights of users \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "You may exercise certain rights regarding your information processed by us. In particular, you have the right to do the following: (i) you have the right to withdraw consent where you have previously given your consent to the processing of your information; (ii) you have the right to object to the processing of your information if the processing is carried out on a legal basis other than consent; (iii) you have the right to learn if information is being processed by us, obtain disclosure regarding certain aspects of the processing and obtain a copy of the information undergoing processing; (iv) you have the right to verify the accuracy of your information and ask for it to be updated or corrected; (v) you have the right, under certain circumstances, to restrict the processing of your information, in which case, we will not process your information for any purpose other than storing it; (vi) you have the right, under certain circumstances, to obtain the erasure of your Personal Information from us; (vii) you have the right to receive your information in a structured, commonly used and machine readable format and, if technically feasible, to have it transmitted to another controller without any hindrance. This provision is applicable provided that your information is processed by automated means and that the processing is based on your consent, on a contract which you are part of or on pre-contractual obligations thereof. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Privacy of children \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "We do not knowingly collect any Personal Information from children under the age of 18. If you are under the age of 18, please do not submit any Personal Information through our Mobile Application or Service. We encourage parents and legal guardians to monitor their children's Internet usage and to help enforce this Policy by instructing their children never to provide Personal Information through our Mobile Application or Service without their permission. If you have reason to believe that a child under the age of 18 has provided Personal Information to us through our Mobile Application or Service, please contact us. You must also be old enough to consent to the processing of your Personal Information in your country (in some countries we may allow your parent or guardian to do so on your behalf). \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Links to other mobile applications \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Our Mobile Application contains links to other mobile applications that are not owned or controlled by us. Please be aware that we are not responsible for the privacy practices of such other mobile applications or third parties. We encourage you to be aware when you leave our Mobile Application and to read the privacy statements of each and every mobile application that may collect Personal Information. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Information security \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "We secure information you provide on computer servers in a controlled, secure environment, protected from unauthorized access, use, or disclosure. We maintain reasonable administrative, technical, and physical safeguards in an effort to protect against unauthorized access, use, modification, and disclosure of Personal Information in its control and custody. However, no data transmission over the Internet or wireless network can be guaranteed. Therefore, while we strive to protect your Personal Information, you acknowledge that (i) there are security and privacy limitations of the Internet which are beyond our control; (ii) the security, integrity, and privacy of any and all information and data exchanged between you and our Mobile Application cannot be guaranteed; and (iii) any such information and data may be viewed or tampered with in transit by a third party, despite best efforts. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Data breach \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "In the event we become aware that the security of the Mobile Application has been compromised or users Personal Information has been disclosed to unrelated third parties as a result of external activity, including, but not limited to, security attacks or fraud, we reserve the right to take reasonably appropriate measures, including, but not limited to, investigation and reporting, as well as notification to and cooperation with law enforcement authorities. In the event of a data breach, we will make reasonable efforts to notify affected individuals if we believe that there is a reasonable risk of harm to the user as a result of the breach or if notice is otherwise required by law. When we do, we will post a notice in the Mobile Application. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Changes and amendments \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "We may update this Privacy Policy from time to time in our discretion and will notify you of any material changes to the way in which we treat Personal Information. When changes are made, we will post a notification in our Mobile Application. We may also provide notice to you in other ways in our discretion, such as through contact information you have provided. Any updated version of this Privacy Policy will be effective immediately upon the posting of the revised Privacy Policy unless otherwise specified. Your continued use of the Mobile Application or Services after the effective date of the revised Privacy Policy (or such other act specified at that time) will constitute your consent to those changes. However, we will not, without your consent, use your Personal Information in a manner materially different than what was stated at the time your Personal Information was collected. Policy was created with https://www.WebsitePolicies.com \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Acceptance of this policy \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "You acknowledge that you have read this Policy and agree to all its terms and conditions. By using the Mobile Application or its Services you agree to be bound by this Policy. If you do not agree to abide by the terms of this Policy, you are not authorized to use or access the Mobile Application and its Services. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Contacting us \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "If you would like to contact us to understand more about this Policy or wish to contact us concerning any matter relating to individual rights and your Personal Information, you may send an email to customercare@smartserve.com \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "This document was last updated on July 7, 2020 \n" +
                    "\n" +
                    " ";
            String terms = "Terms and conditions \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "These terms and conditions (\"Terms\", \"Agreement\") are an agreement between Mobile Application Developer (\"Mobile Application Developer\", \"us\", \"we\" or \"our\") and you (\"User\", \"you\" or \"your\"). This Agreement sets forth the general terms and conditions of your use of the Smart Serve mobile application and any of its products or services (collectively, \"Mobile Application\" or \"Services\"). \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Age requirement \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "You must be at least 16 years of age to use this Mobile Application. By using this Mobile Application and by agreeing to this Agreement you warrant and represent that you are at least 18 years of age. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Links to other mobile applications \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Although this Mobile Application may link to other mobile applications, we are not, directly or indirectly, implying any approval, association, sponsorship, endorsement, or affiliation with any linked mobile application, unless specifically stated herein. Some of the links in the Mobile Application may be \"affiliate links\". This means if you click on the link and purchase an item, Mobile Application Developer will receive an affiliate commission. We are not responsible for examining or evaluating, and we do not warrant the offerings of, any businesses or individuals or the content of their mobile applications. We do not assume any responsibility or liability for the actions, products, services, and content of any other third parties. You should carefully review the legal statements and other conditions of use of any mobile application which you access through a link from this Mobile Application. Your linking to any other off-site mobile applications is at your own risk. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Prohibited uses \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "In addition to other terms as set forth in the Agreement, you are prohibited from using the Mobile Application or its Content: (a) for any unlawful purpose; (b) to solicit others to perform or participate in any unlawful acts; (c) to violate any international, federal, provincial or state regulations, rules, laws, or local ordinances; (d) to infringe upon or violate our intellectual property rights or the intellectual property rights of others; (e) to harass, abuse, insult, harm, defame, slander, disparage, intimidate, or discriminate based on gender, sexual orientation, religion, ethnicity, race, age, national origin, or disability; (f) to submit false or misleading information; (g) to upload or transmit viruses or any other type of malicious code that will or may be used in any way that will affect the functionality or operation of the Service or of any related mobile application, other mobile applications, or the Internet; (h) to spam, phish, pharm, pretext, spider, crawl, or scrape; (i) for any obscene or immoral purpose; or (j) to interfere with or circumvent the security features of the Service or any related mobile application, other mobile applications, or the Internet. We reserve the right to terminate your use of the Service or any related mobile application for violating any of the prohibited uses. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Intellectual property rights \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "\"Intellectual Property Rights\" means all present and future rights conferred by statute, common law or equity in or in relation to any copyright and related rights, trademarks, designs, patents, inventions, goodwill and the right to sue for passing off, rights to inventions, rights to use, and all other intellectual property rights, in each case whether registered or unregistered and including all applications and rights to apply for and be granted, rights to claim priority from, such rights and all similar or equivalent rights or forms of protection and any other results of intellectual activity which subsist or will subsist now or in the future in any part of the world. This Agreement does not transfer to you any intellectual property owned by Mobile Application Developer or third parties, and all rights, titles, and interests in and to such property will remain (as between the parties) solely with Mobile Application Developer. All trademarks, service marks, graphics and logos used in connection with the Mobile Application or Services, are trademarks or registered trademarks of Mobile Application Developer or Mobile Application Developer licensors. Other trademarks, service marks, graphics and logos used in connection with the Mobile Application or Services may be the trademarks of other third parties. Your use of the Mobile Application and Services grants you no right or license to reproduce or otherwise use any Mobile Application Developer or third party trademarks. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Limitation of liability \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "To the fullest extent permitted by applicable law, in no event will Mobile Application Developer, its affiliates, directors, officers, employees, agents, suppliers or licensors be liable to any person for any indirect, incidental, special, punitive, cover or consequential damages (including, without limitation, damages for lost profits, revenue, sales, goodwill, use of content, impact on business, business interruption, loss of anticipated savings, loss of business opportunity) however caused, under any theory of liability, including, without limitation, contract, tort, warranty, breach of statutory duty, negligence or otherwise, even if the liable party has been advised as to the possibility of such damages or could have foreseen such damages. To the maximum extent permitted by applicable law, the aggregate liability of Mobile Application Developer and its affiliates, officers, employees, agents, suppliers and licensors relating to the services will be limited to an amount greater of one dollar or any amounts actually paid in cash by you to Mobile Application Developer for the prior one month period prior to the first event or occurrence giving rise to such liability. The limitations and exclusions also apply if this remedy does not fully compensate you for any losses or fails of its essential purpose. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Indemnification \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "You agree to indemnify and hold Mobile Application Developer and its affiliates, directors, officers, employees, agents, suppliers and licensors harmless from and against any liabilities, losses, damages or costs, including reasonable attorneys' fees, incurred in connection with or arising from any third party allegations, claims, actions, disputes, or demands asserted against any of them as a result of or relating to your Content, your use of the Mobile Application or Services or any willful misconduct on your part. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Severability \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "All rights and restrictions contained in this Agreement may be exercised and shall be applicable and binding only to the extent that they do not violate any applicable laws and are intended to be limited to the extent necessary so that they will not render this Agreement illegal, invalid or unenforceable. If any provision or portion of any provision of this Agreement shall be held to be illegal, invalid or unenforceable by a court of competent jurisdiction, it is the intention of the parties that the remaining provisions or portions thereof shall constitute their agreement with respect to the subject matter hereof, and all such remaining provisions or portions thereof shall remain in full force and effect. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Dispute resolution \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "The formation, interpretation, and performance of this Agreement and any disputes arising out of it shall be governed by the substantive and procedural laws of Karnataka, India without regard to its rules on conflicts or choice of law and, to the extent applicable, the laws of India. The exclusive jurisdiction and venue for actions related to the subject matter hereof shall be the courts located in Karnataka, India, and you hereby submit to the personal jurisdiction of such courts. You hereby waive any right to a jury trial in any proceeding arising out of or related to this Agreement. The United Nations Convention on Contracts for the International Sale of Goods does not apply to this Agreement. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Changes and amendments \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "We reserve the right to modify this Agreement or its policies relating to the Mobile Application or Services at any time, effective upon posting of an updated version of this Agreement in the Mobile Application. When we do, we will post a notification in our Mobile Application. Continued use of the Mobile Application after any such changes shall constitute your consent to such changes. Policy was created with https://www.WebsitePolicies.com \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Acceptance of these terms \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "You acknowledge that you have read this Agreement and agree to all its terms and conditions. By using the Mobile Application or its Services you agree to be bound by this Agreement. If you do not agree to abide by the terms of this Agreement, you are not authorized to use or access the Mobile Application and its Services. \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Contacting us \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "If you would like to contact us to understand more about this Agreement or wish to contact us concerning any matter relating to it, you may send an email to customercare@smartserve.com \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "This document was last updated on July 7, 2020 \n" +
                    "\n" +
                    " ";

            Intent intent = getIntent();
            String diskey = intent.getStringExtra("DisKey");

            // ErrorHandling.ErrorDialog(diskey,this);

            if (diskey.equalsIgnoreCase("Privacy")) {
                distext.setText(privacy);
                //  ErrorHandling.ErrorDialog(diskey,this);
            } else if (diskey.equalsIgnoreCase("Disclaimer")) {
                distext.setText(disclaimer);
                //ErrorHandling.ErrorDialog(diskey,this);
            } else {
                distext.setText(terms);
            }
        }
        catch (Exception ex){
            ErrorHandling.ErrorDialog(ex.getMessage(),this);
        }

    }
    public void GotoPrivacy(View v){
        Intent intent = new Intent(this, Disclaimer.class);
        intent.putExtra("DisKey","Privacy");
        startActivity(intent);
    }
    public void GotoDisclaimer(View v){
        Intent intent = new Intent(this, Disclaimer.class);
        intent.putExtra("DisKey","Disclaimer");
        startActivity(intent);
    }
    public void GotoTerms(View v){
        Intent intent = new Intent(this, Disclaimer.class);
        intent.putExtra("DisKey","Terms");
        startActivity(intent);
    }
}
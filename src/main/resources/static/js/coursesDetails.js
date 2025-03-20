

// Fill in the course details

const courses = [
    {
        productID: 1,
        title: "Real-Time Programming in Java",
        coveredTopics: "Java, real-time programming, multi-threading, programming",
        level: "Expert",
        closestCourseSession: "June 3rd – June 28th",
        courseSize: "7.5 ECTS credits",
        hoursPerWeek: 40,
        relatedCertifications: "Java SE 17 Programmer Professional",
        courseProviders: [
            { provider: "NTNU", price: "29’999 NOK" },
            { provider: "Oracle", price: "32’000 NOK" }
        ],
        description: "Embark on a transformative learning experience with our expert-level online course, \"RealTime Programming in Java.\" Designed for seasoned developers and Java enthusiasts seeking mastery in\n" +
            "real-time applications, this advanced course delves deep into the intricacies of leveraging Java for\n" +
            "mission-critical systems. Explore cutting-edge concepts such as multithreading, synchronization, and\n" +
            "low-latency programming, equipping you with the skills needed to build responsive and robust real-time\n" +
            "solutions. Led by industry experts with extensive hands-on experience, this course combines theoretical\n" +
            "insights with practical application, ensuring you not only grasp the theoretical underpinnings but also\n" +
            "gain the proficiency to implement real-time solutions confidently. Elevate your Java programming\n" +
            "expertise to new heights and stay ahead in the ever-evolving landscape of real-time systems with our\n" +
            "comprehensive and immersive course."
    },
    {
        productID: 2,
        title: " Introduction to SQL Essentials",
        coveredTopics: "SQL, relational databases, MySQL",
        level: "Beginner",
        closestCourseSession: "June 10rd – June 28th",
        courseSize: "2 ECTS credits",
        hoursPerWeek: 20,
        relatedCertifications: "SQL Fundamentals",
        courseProviders: [
            { provider: "Apache Software Foundation", price: "800 USD" },
            { provider: "NTNU", price: "10’000 NOK" },
            { provider: "Pearson", price: "899 USD" }
        ],
        description: "Dive into the fundamentals of database management with our beginner-level online course,\n" +
            "\"Introduction to SQL Essentials.\" Geared towards those new to the world of databases and SQL, this\n" +
            "course provides a comprehensive foundation for understanding and utilizing SQL for effective data\n" +
            "management. While MySQL is touched upon to broaden your practical knowledge, the core focus is on\n" +
            "SQL's universal principles applicable across various database systems. Led by seasoned instructors, the\n" +
            "course covers database design, querying data, and basic data manipulation using SQL commands. With a\n" +
            "hands-on approach, you'll engage in practical exercises to reinforce your learning, ensuring you gain the\n" +
            "skills necessary to navigate and interact with databases confidently. Whether you're a budding\n" +
            "developer, analyst, or anyone eager to harness the power of databases, this course offers an accessible\n" +
            "entry point into the world of SQL, setting the stage for your future success in data-driven environments.\n"
    },
    {
        productID: 3,
        title: "Creating Web Application with .Net",
        coveredTopics: "web, programming, .net, C#",
        level: "Beginner",
        closestCourseSession: "Aug 5th – Aug 16th",
        courseSize: "4 ECTS credits",
        hoursPerWeek: 40,
        relatedCertifications: ".Net Developer Fundamentals",
        courseProviders: [
            { provider: "Microsoft", price: "2999 NOK" },
            { provider: "Pearson", price: "3000 NOK" },
            { provider: "Oracle", price: "200 USD" }
        ],
        description: "Embark on your journey into web development with our beginner-level online course,\n" +
            "\"Creating Web Applications with .NET.\" Tailored for those stepping into the dynamic world of web\n" +
            "development, this course provides a solid foundation in utilizing the versatile .NET framework to build\n" +
            "powerful and interactive web applications. Guided by experienced instructors, you'll explore\n" +
            "fundamental concepts such as web application architecture, user interface design, and server-side\n" +
            "scripting using .NET technologies like ASP.NET. Throughout the course, you'll engage in hands-on\n" +
            "projects that walk you through the entire development process, from designing responsive user\n" +
            "interfaces to implementing server-side functionality. Gain practical skills in C# programming and\n" +
            "discover how to leverage the robust features of .NET to bring your web applications to life. Whether\n" +
            "you're a programming novice or transitioning from another language, this course offers a welcoming\n" +
            "entry point into the exciting realm of web application development with .NET, setting you on a path to\n" +
            "create dynamic and engaging online experiences."
    },
    {
        productID: 4,
        title: "Azure Cloud Fundamentals",
        coveredTopics: "Azure, cloud services",
        level: "Beginner",
        closestCourseSession: "July 1st – July 31st",
        courseSize: "2 ECTS credits",
        hoursPerWeek: 10,
        relatedCertifications: "AZ-900 Azure Fundamentals",
        courseProviders: [
            { provider: "Microsoft", price: "200 USD" },
            { provider: "NTNU", price: "1800 NOK" },
            { provider: "Pearson", price: "200 USD" }
        ],
        description: "Embark on your cloud computing journey with our beginner-level online course, \"Azure\n" +
            "Fundamentals,\" meticulously crafted to prepare you for the AZ-900 exam. Whether you're new to cloud\n" +
            "technologies or seeking to validate your foundational knowledge, this course provides a comprehensive\n" +
            "introduction to Microsoft Azure, one of the industry's leading cloud platforms. Delve into the essentials\n" +
            "of cloud concepts, Azure services, pricing, and compliance, all while guided by expert instructors who\n" +
            "understand the importance of building a strong cloud foundation. Through a combination of engaging\n" +
            "lectures, hands-on labs, and real-world scenarios, you'll gain practical insights into deploying solutions\n" +
            "on Azure and mastering fundamental cloud principles. By the end of the course, you'll not only be wellprepared to ace the AZ-900 exam but will also have a solid understanding of Azure's capabilities,\n" +
            "empowering you to confidently navigate the vast landscape of cloud computing. Join us on this\n" +
            "educational journey and unlock the potential of cloud technology with Azure Fundamentals.\n"
    },

    {
        productID: 5,
        title: "Azure Administration (Intermediate)",
        coveredTopics: "Azure, cloud services, administration",
        level: "Intermediate",
        closestCourseSession: "June 15th – July 10th",
        courseSize: "5 ECTS credits",
        hoursPerWeek: 30,
        relatedCertifications: "Python Programming Professional",
        courseProviders: [
            {provider: "NTNU", price: "15’000 NOK"},
            {provider: "Pearson", price: "1500 NOK"},
            {provider: "Oracle", price: "150 USD"}
        ],
        description:"Elevate your cloud expertise with our intermediate-level online course, \"Azure\n" +
            "Administrator,\" meticulously designed to prepare you for the AZ-104 exam – your gateway to becoming\n" +
            "a Microsoft Certified Cloud Administrator. Tailored for individuals with a foundational understanding of\n" +
            "Azure, this course takes a deep dive into advanced administration and management tasks, honing the\n" +
            "skills required for efficient cloud operations. Led by seasoned Azure professionals, you'll explore\n" +
            "intricate topics such as virtual networking, identity management, and governance strategies, gaining\n" +
            "hands-on experience through practical exercises and real-world scenarios. The course's comprehensive\n" +
            "coverage aligns seamlessly with the AZ-104 exam objectives, ensuring that you not only pass the\n" +
            "certification but emerge as a proficient Azure Administrator capable of implementing robust cloud\n" +
            "solutions. Whether you're looking to enhance your career or solidify your position as a cloud expert, this\n" +
            "course is your key to mastering the intricacies of Azure administration and achieving Microsoft Certified\n" +
            "Cloud Administrator status. Join us on this transformative journey towards advanced Azure proficiency."
    },

    {
        productID: 6,
        title: "AWS Cloud Practitioner",
        coveredTopics: "AWS, cloud services",
        level: "Beginner",
        closestCourseSession: "September 9th – September 20th (2 weeks)",
        courseSize: "2 ECTS credits",
        hoursPerWeek: 20,
        relatedCertifications: " CLF-C02 AWS Certified Cloud Practitioner",
        courseProviders: [
            {provider: "Amazon", price: "100 USD"},
            {provider: "Pearson", price: "120 USD"},
            {provider: "NTNU", price: "1800 NOK"}
        ],
        description: " Discover the fundamentals of cloud computing in our beginner-level online course, \"AWS\n" +
            "Cloud Practitioner,\" designed to prepare you for the CLF-C02 certification exam. Tailored for individuals\n" +
            "with minimal prior experience in cloud technologies, this course provides a robust foundation in\n" +
            "understanding the essential concepts of Amazon Web Services (AWS). Led by experienced AWS\n" +
            "professionals, the course delves into core topics, including cloud architecture, AWS services, security,\n" +
            "and pricing models. Through dynamic lectures and hands-on labs, you'll gain practical insights into\n" +
            "navigating the AWS console, setting up basic infrastructure, and comprehending key cloud principles. By\n" +
            "the course's end, you'll be well-equipped to excel in the CLF-C02 exam and possess a foundational\n" +
            "understanding of AWS, empowering you to confidently explore and leverage cloud services. Join us in\n" +
            "this educational journey, and initiate your AWS Cloud Practitioner certification with assurance and\n" +
            "proficiency.\n"
    },

    {
        productID: 7,
        title: "Search Engine Optimization",
        coveredTopics: "keyword research and analysis, technical SEO optimization, off-page SEO" +
            "strategies, advanced analytics and reporting",
        level: "Intermediate",
        closestCourseSession: "Aug 5th – Auth 30th",
        courseSize: "2 ECTS credits",
        hoursPerWeek: 4,
        relatedCertifications: "SEO Wizard",
        courseProviders: [
            {provider: "Adobe", price: "66’000 NOK"},
            {provider: "Apple", price: "80'000 NOK"},
            {provider: "Google", price: "6000 USD"},
            {provider: "Microsoft", price: "6000 USD"},
            {provider: "Amazon", price: "6000 USD"}
        ],
        description: "Deepen your expertise in the digital landscape with our intermediate-level online course,\n" +
            "\"Search Engine Optimization (SEO).\" Tailored for marketers, business owners, and digital enthusiasts\n" +
            "looking to refine their online presence, this course takes a comprehensive dive into the intricacies of\n" +
            "SEO strategies and techniques. Led by seasoned SEO professionals, the course covers advanced topics\n" +
            "such as keyword research, on-page and off-page optimization, technical SEO, and analytics. Through a\n" +
            "blend of theoretical insights and practical exercises, you'll learn how to enhance website visibility,\n" +
            "improve search engine rankings, and drive organic traffic effectively. Stay ahead in the ever-evolving\n" +
            "digital landscape by mastering the art and science of SEO. Whether you're aiming to boost your\n" +
            "business's online visibility or embark on a career in digital marketing, this course equips you with the\n" +
            "skills and knowledge needed to navigate the complexities of SEO with confidence and success. Join us\n" +
            "and elevate your digital presence with our intermediate-level SEO course."
    },

    {
        productID: 8,
        title: "Social Media Marketing",
        coveredTopics: "strategic storytelling, targeted engagement techniques, data-driven" + "optimization",
        level: "Intermediate",
        closestCourseSession: "Aug 5th – Auth 30th",
        courseSize: "2 ECTS credits",
        hoursPerWeek: 4,
        relatedCertifications: "Certified Social Alchemist",
        courseProviders: [
            {provider: "Adobe", price: "66’000 NOK"},
            {provider: "Apple", price: "80'000 NOK"},
            {provider: "Google", price: "6000 USD"},
            {provider: "Microsoft", price: "6000 USD"},
            {provider: "Amazon", price: "6000 USD"}
        ],
        description: "Elevate your digital marketing prowess with our intermediate-level online course, \"Social\n" +
            "Media Marketing.\" Tailored for marketers, business professionals, and enthusiasts seeking to harness\n" +
            "the power of social platforms, this course provides an in-depth exploration of advanced social media\n" +
            "marketing strategies. Led by industry experts, you'll delve into nuanced topics such as audience\n" +
            "targeting, content optimization, social media advertising, and analytics. Through a blend of theoretical\n" +
            "insights and hands-on exercises, you'll gain practical skills to create compelling social media campaigns,\n" +
            "foster audience engagement, and measure the impact of your efforts. Stay at the forefront of digital\n" +
            "marketing trends by mastering the art of crafting impactful narratives, building brand loyalty, and\n" +
            "leveraging diverse social channels. Whether you aim to enhance your business's online presence or\n" +
            "advance your career in digital marketing, this course equips you with the tools and knowledge to\n" +
            "navigate the dynamic landscape of social media marketing with confidence and proficiency. Join us and\n" +
            "amplify your social media marketing expertise with our intermediate-level course.\n"
    },

    {
        productID: 9,
        title: "Business Strategy",
        coveredTopics: "",
        level: "",
        closestCourseSession: " June 3rd – November 29th",
        courseSize: "10 ECTS credits",
        hoursPerWeek: 10,
        relatedCertifications: "Certified Strategic Business Architect (CSBA)\n",
        courseProviders: [
            {provider: "NTNU", price: "50’000 NOK"},
            {provider: "Microsoft", price: "50’000 NOK"},
            {provider: "Handelshøyskolen BI", price: "50’000 NOK"}
        ],
        description: "Master the art of strategic thinking with our expert-level online course, \"Business Strategy.\"\n" +
            "Tailored for seasoned professionals, entrepreneurs, and strategic leaders, this course offers an\n" +
            "immersive exploration of advanced business strategy concepts and applications. Led by industry thought\n" +
            "leaders, you'll delve into intricate topics such as competitive analysis, market positioning, disruptive\n" +
            "innovation, and global strategic management. Through case studies, simulations, and real-world\n" +
            "scenarios, you'll sharpen your ability to make informed strategic decisions that drive long-term success.\n" +
            "This course goes beyond the basics, challenging you to synthesize diverse business elements into a\n" +
            "cohesive and forward-thinking strategy. Whether you aspire to lead a multinational corporation or\n" +
            "refine your entrepreneurial ventures, our expert-level Business Strategy course empowers you to\n" +
            "navigate complex business landscapes with foresight and precision. Join us in this transformative\n" +
            "learning journey and elevate your strategic acumen to new heights."
    },

    {
        productID: 10,
        title: "Machine Learning Basics with Python",
        coveredTopics: "Python, machine learning, programming, data science",
        level: "Beginner",
        closestCourseSession: "Aug 19th – Augh 30th",
        courseSize: "2 ECTS credits",
        hoursPerWeek: 10,
        relatedCertifications: "Machine Learning Fundamentals",
        courseProviders: [
            {provider: "NTNU", price: "20’000 NOK"},
            {provider: "University og Oslo", price: "20’000 NOK"},
            {provider: "University og Bergen", price: "20’000 NOK"}
        ],
        description: " Embark on your journey into the exciting realm of artificial intelligence with our beginnerlevel online course, \"Machine Learning Basics with Python.\" Tailored for individuals new to the world of\n" +
            "machine learning, this course provides a comprehensive introduction to the fundamental concepts and\n" +
            "techniques using the versatile Python programming language. Led by experienced instructors, you'll\n" +
            "explore the basics of supervised and unsupervised learning, delve into popular machine learning\n" +
            "algorithms, and gain hands-on experience through practical exercises. No prior coding experience is\n" +
            "required, making this course an ideal starting point for beginners eager to grasp the essentials of\n" +
            "machine learning. By the end of the course, you'll have a solid foundation in using Python for machine\n" +
            "learning applications, empowering you to unravel the mysteries of data and embark on a fascinating\n" +
            "journey into the world of intelligent algorithms. Join us and demystify the basics of machine learning\n" +
            "with Python in this accessible and empowering course."

    },

    {
        productID: 11,
        title: "Image Recognition",
        coveredTopics: "Python, machine learning, programming, data science, neural networks," + "image processing",
        level: "Intermediate",
        closestCourseSession: "Sep 2nd – Sep 27th (4 weeks)",
        courseSize: "4 ECTS credits",
        hoursPerWeek: 20,
        relatedCertifications: "Machine Vision Associate\n",
        courseProviders: [
            {provider: "NTNU", price: "30’000 NOK"},
            {provider: "University og Oslo", price: "30’000 NOK"},
            {provider: "University og Bergen", price: "30’000 NOK"}
        ],
        description: "Deepen your expertise in the realm of artificial intelligence with our intermediate-level\n" +
            "online course, \"Image Recognition with Python.\" Tailored for those with a foundational understanding of\n" +
            "machine learning, this course immerses you in the intricacies of image recognition techniques and\n" +
            "technologies using the powerful Python programming language. Led by seasoned instructors, you'll\n" +
            "explore advanced concepts such as convolutional neural networks (CNNs), image preprocessing, and\n" +
            "transfer learning. Through hands-on projects and real-world applications, you'll sharpen your skills in\n" +
            "training models to recognize and classify images with precision. This course is ideal for individuals\n" +
            "looking to expand their knowledge in computer vision and image processing, and it serves as a stepping\n" +
            "stone for professionals aspiring to integrate image recognition capabilities into their projects. Join us in\n" +
            "this intermediate-level course, and unlock the potential of image recognition with Python, advancing\n" +
            "your proficiency in the exciting field of artificial intelligence."
    },

    {
        productID: 12,
        title: " Databricks fundamentals",
        coveredTopics: "Python, machine learning, programming, data science, neural networks," + "Databricks",
        level: "Beginner",
        closestCourseSession: "Aug 19th – Aug 30th",
        courseSize: "2 ECTS credits",
        hoursPerWeek: 10,
        relatedCertifications: "Databricks Practitioner",
        courseProviders: [
            {provider: "NTNU", price: "20’000 NOK"},
            {provider: "University og Oslo", price: "20’000 NOK"},
            {provider: "University og Bergen", price: "20’000 NOK"}
        ],
        description: "Embark on your data journey with our beginner-level online course, \"Databricks\n" +
            "Fundamentals.\" Designed for individuals new to the world of big data and analytics, this course offers a\n" +
            "comprehensive introduction to the essential concepts of Databricks, a leading unified analytics platform.\n" +
            "Led by experienced instructors, you'll navigate through the fundamentals of data exploration, data\n" +
            "engineering, and collaborative data science using Databricks. No prior experience with big data\n" +
            "technologies is required, making this course an ideal starting point for beginners eager to harness the\n" +
            "power of Databricks for streamlined data processing and analysis. By the end of the course, you'll have a\n" +
            "solid foundation in using Databricks to uncover insights from large datasets, setting you on a path\n" +
            "towards mastering the intricacies of modern data analytics. Join us and demystify the fundamentals of\n" +
            "Databricks in this accessible and empowering course."
    },
];



    // Method to get the course details based on the productID
    function getCourseById(productID) {
    return courses.find(course => course.productID === productID);
    }


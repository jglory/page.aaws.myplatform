<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('member', function (Blueprint $table) {
            $table->id()->comment('고유키');
            $table->string('email', 100)->comment('이메일');
            $table->string('type', 10)->comment('회원 타입. PLATFORM, CUSTOMER, PARTNER');
            $table->string('password', 255)->comment('비밀번호');
            $table->dateTime('created_at')->comment("생성일시");
            $table->dateTime('deleted_at')->index()->comment('삭제일시');

            $table->primary('id');
            $table->unique(['type', 'email']);
            $table->index("created_at");
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('member');
    }
};
